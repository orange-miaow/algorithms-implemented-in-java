package org.example.branch_and_bound;

import java.util.LinkedHashSet;

public class TSP {

    // 城市数
    private int n;
    // [i][j] 城市i至城市j的距离 -1表示不可达
    private int[][] w;
    // [i] 城市i出发的最短边的边长
    private int[] minEdgeWeight;

    // 界函数值 -1表示正无穷
    private int lowerBoundFunctionValue;
    // 解向量
    private int[] solutionVector;

    public TSP(int n, int[][] w) {
        this.n = n;
        this.w = w;
        this.minEdgeWeight = new int[n];

        for (int i = 0; i < n; i++) {
            int min = -1;
            for (int j = 0; j < n; j++) {
                if ((w[i][j] > 0) && ((min == -1) || (w[i][j] < min))) {
                    min = w[i][j];
                }
            }
            minEdgeWeight[i] = min;
        }

        this.lowerBoundFunctionValue = -1;
    }

    public int getLowerBoundFunctionValue() {
        return lowerBoundFunctionValue;
    }

    public int[] getSolutionVector() {
        return solutionVector;
    }

    /**
     * 在搜索树中搜寻最优解
     *
     * @param level  层级 level=1,2,...,n-1
     * @param vector 根结点至当前结点的向量
     */
    public void searchOptimalSolution(int level, int[] vector) {
        // 获取已访问顶点集中的最后一个顶点
        Integer lastVisitedVertex = vector[level - 1];
        // 未访问顶点集 不包括驻地城市顶点
        LinkedHashSet<Integer> unvisitedVertexSet = new LinkedHashSet<>() {{
            for (int i = 1; i < n; i++) {
                add(i);
            }
        }};

        // 父结点路径长度
        int parentPathLength = 0;
        // 父结点代价函数值
        int parentCostFunctionValue = 0;

        for (int i = 1; i < level; i++) {
            unvisitedVertexSet.remove(vector[i]);
            parentPathLength += w[vector[i - 1]][vector[i]];
        }

        parentCostFunctionValue = parentPathLength + minEdgeWeight[lastVisitedVertex];
        for (Integer unvisitedVertex : unvisitedVertexSet) {
            parentCostFunctionValue += minEdgeWeight[unvisitedVertex];
        }

        for (Integer currentVertex : unvisitedVertexSet) { // 获取未访问顶点集中的一个顶点
            // (判断 根结点至该结点的向量 是否 为部分可行解向量) 且 (判断 该结点的代价函数值 是否 小于界函数值)
            if (w[lastVisitedVertex][currentVertex] == -1) {
                // 顶点lastVisitedVertex 与 顶点currentVertex 没有边
                continue;
            }
            if ((level == n - 1) && (w[currentVertex][0] == -1)) {
                // 已经到达叶结点 即最后一个顶点
                // 顶点currentVertex 与 顶点0 没有边
                // 无法构成回路
                continue;
            }

            vector[level] = currentVertex;
            if (level == n - 1) {
                // 已经到达叶结点 即最后一个顶点
                // 计算回路长度
                int circuitLength = parentPathLength + w[lastVisitedVertex][currentVertex] + w[currentVertex][0];
                if ((lowerBoundFunctionValue == -1) || (circuitLength < lowerBoundFunctionValue)) {
                    lowerBoundFunctionValue = circuitLength;
                    solutionVector = vector.clone();
                }
                continue;
            }

            // 计算代价函数值
            int costFunctionValue = parentCostFunctionValue - minEdgeWeight[lastVisitedVertex] + w[lastVisitedVertex][currentVertex];
            if ((lowerBoundFunctionValue == -1) || (costFunctionValue < lowerBoundFunctionValue)) {
                // 根据深度优先策略(DFS)遍历该结点的下一个结点
                searchOptimalSolution(level + 1, vector);
            } else {
                // 由该结点回溯至其父结点 之后根据深度优先策略(DFS)遍历父结点的下一个可用结点
            }
        }
    }

}
