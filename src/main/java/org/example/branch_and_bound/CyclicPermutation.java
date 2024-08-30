package org.example.branch_and_bound;

import java.util.ArrayList;
import java.util.List;

public class CyclicPermutation {

    // 圆的个数
    int n;
    // [i] 圆i的半径
    int[] r;

    // 界函数值 1表示正无穷
    private double lowerBoundFunctionValue;
    // 解向量
    private int[] solutionVector;

    public CyclicPermutation(int[] r) {
        this.r = r;
        this.n = r.length;

        this.lowerBoundFunctionValue = -1;
    }

    public double getLowerBoundFunctionValue() {
        return lowerBoundFunctionValue;
    }

    public int[] getSolutionVector() {
        return solutionVector;
    }

    /**
     * 在搜索树中搜寻最优解
     *
     * @param level  层级 level=0,1,2,...,n
     * @param vector 根结点至当前结点的向量
     */
    public void searchOptimalSolution(int level, int[] vector, double lastCycleX) {
        // 访问状态数组
        int[] visitedStateArray = new int[n];
        for (int i = 0; i < level; i++) {
            visitedStateArray[vector[i]] = 1;
        }
        // 未访问的圆集合
        List<Integer> unvisitedList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (visitedStateArray[i] != 1) {
                unvisitedList.add(i);
            }
        }

        // 预计算值minRadius
        // 计算第level个位置以及后面待选的n-1-level个位置所放的圆中最小半径的值
        int minRadius = r[unvisitedList.get(0)];
        for (int i = 1; i < unvisitedList.size(); i++) {
            if (r[unvisitedList.get(i)] < minRadius) {
                minRadius = r[unvisitedList.get(i)];
            }
        }

        for (Integer currentCycle : unvisitedList) {
            // 判断 该结点的代价函数值 是否 小于界函数值

            // 第level个位置放置圆currentCycle
            vector[level] = currentCycle;

            // 第level个位置所放的圆的圆心横坐标
            // 规定第0个位置所放的圆的圆心为坐标原点
            double currentCycleX = 0;
            if (level != 0) {
                // 第level个位置所放的圆的圆心横坐标与第level-1个位置所放的圆的圆心横坐标的差
                double d = 2 * Math.sqrt(r[vector[level - 1]] * r[vector[level]]);
                currentCycleX = lastCycleX + d;
            }

            if (level == n - 1) {
                // 已到达叶节点 即最后一个圆
                // 计算排列长度
                double l = currentCycleX + r[vector[0]] + r[vector[level]];
                if ((lowerBoundFunctionValue == -1) || (l < lowerBoundFunctionValue)) {
                    lowerBoundFunctionValue = l;
                    solutionVector = vector.clone();
                }
                continue;
            }

            // 计算代价函数值
            // 求第level个位置以及后面待选的n-1-level个位置所放的圆中最小半径的值
            // 使用预计算值minRadius即可
            double costFunctionValue = currentCycleX + (2 * n - 2 * (level + 1) + 1) * minRadius + r[vector[0]];

            if ((lowerBoundFunctionValue == -1) || (costFunctionValue < lowerBoundFunctionValue)) {
                // 根据深度优先策略(DFS)遍历该结点的下一个结点
                searchOptimalSolution(level + 1, vector, currentCycleX);
            } else {
                // 剪枝
                // 由该结点回溯至其父结点 之后根据深度优先策略(DFS)遍历父结点的下一个可用结点
            }
        }
    }

}
