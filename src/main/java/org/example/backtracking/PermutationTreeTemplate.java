package org.example.backtracking;

import java.util.ArrayList;
import java.util.List;

// 排列树
public abstract class PermutationTreeTemplate {

    // 从n个不同元素中取出n个元素
    private final int n;
    // 可行解向量列表
    private final List<int[]> possibleSolutionVectors = new ArrayList<>();

    public PermutationTreeTemplate(int n) {
        this.n = n;
    }

    public List<int[]> getPossibleSolutionVectors() {
        return possibleSolutionVectors;
    }

    /**
     * 在排列树中搜寻可行解
     *
     * @param level  层级 level=0,1,2,...,n-1
     * @param vector 根结点至当前结点的向量
     */
    public void searchPossibleSolutions(int level, int[] vector) {
        if (level == n) {
            // 此时已遍历到叶结点 且 根结点至该结点的向量为可行解向量
            possibleSolutionVectors.add(vector.clone());
            return;
        }

        // 可用元素
        List<Integer> availableElements = new ArrayList<>();
        // 元素[0..n-1]使用情况
        int[] used = new int[n];

        for (int i = 0; i < level; i++) {
            used[vector[i]] = 1;
        }
        for (int i = 0; i < used.length; i++) {
            if (used[i] == 0) { // 元素i未被使用
                availableElements.add(i);
            }
        }

        for (Integer e : availableElements) {
            // 判断 根结点至该结点的向量 是否 为部分可行解向量
            vector[level] = e;
            if (whetherTheConstraintConditionsAreMet(level, vector)) {
                // 如果满足约束条件 即为部分可行解 则根据深度优先策略(DFS)遍历该结点的下一个结点
                searchPossibleSolutions(level + 1, vector);
            } else {
                // 如果不满足约束条件 即非部分可行解 则该结点及其子树皆不满足约束条件
                // 由该结点回溯至其父结点 之后根据深度优先策略(DFS)遍历父结点的下一个可用结点
            }
        }

    }

    // 判断 根结点至当前结点的向量 是否 满足约束条件
    abstract boolean whetherTheConstraintConditionsAreMet(int level, int[] vector);

}
