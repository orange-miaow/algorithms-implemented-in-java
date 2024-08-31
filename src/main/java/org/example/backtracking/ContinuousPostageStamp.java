package org.example.backtracking;

import org.example.dynamic_programming.MinStampNum;

public class ContinuousPostageStamp {

    // 邮票种类数
    int n;
    // 邮票张数
    int m;

    // 解向量
    private int[] solutionVector;
    // 连续邮资区间的最大长度
    private int max;

    public ContinuousPostageStamp(int n, int m) {
        this.n = n;
        this.m = m;
    }

    public int[] getSolutionVector() {
        return solutionVector;
    }

    public int getMax() {
        return max;
    }

    /**
     * 在搜索树中搜寻最优解
     *
     * @param level  层级 level=0,1,...,n-1
     * @param vector 根结点至当前结点的向量
     * @param r      [i] 在当前结点的最大连续邮资区间为{1,...,r[i]}
     */
    public void searchOptimalSolution(int level, int[] vector, int[] r) {
        // 确定当前结点的分支数
        if (level == 0) {
            r[level] = m;
            vector[level] = 1;
        } else {
            r[level] = r[level - 1];

            int[] x = new int[level + 1 + 1];
            for (int i = 0; i <= level; i++) {
                x[i + 1] = vector[i];
            }

            for (int p = r[level - 1] + 1; p <= m * vector[level]; p++) {
                int[][] y = new int[x.length][p + 1];
                MinStampNum.iterative(x, p, y, new int[x.length][p + 1]);
                if (y[x.length - 1][p] <= m) {
                    r[level] = p;
                } else {
                    break;
                }
            }
        }

        if (level == n - 1) {
            // 已到达叶结点 即最后一种邮票
            if (r[level] > max) {
                solutionVector = vector.clone();
                max = r[level];
            }
            return;
        }

        for (int i = vector[level] + 1; i <= r[level] + 1; i++) {
            vector[level + 1] = i;
            // 根据深度优先策略(DFS)遍历该结点的下一个结点
            searchOptimalSolution(level + 1, vector, r);
        }
    }

}
