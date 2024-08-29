package org.example.branch_and_bound;

public class MaximumClique {

    // 顶点数
    private int n;
    // [i][j] 顶点i与顶点j之间是否有边 1有 0无
    private int[][] edges;

    // 界函数值
    private int upperBoundFunctionValue;
    // 解向量
    private int[] solutionVector;

    public MaximumClique(int n, int[][] edges) {
        this.n = n;
        this.edges = edges;

        this.upperBoundFunctionValue = 0;
    }

    public int getUpperBoundFunctionValue() {
        return upperBoundFunctionValue;
    }

    public int[] getSolutionVector() {
        return solutionVector;
    }

    public void searchOptimalSolution() {
        int[] vector = new int[n];

        int k = 0;
        while (true) {
            // 从第k层自上向下寻找一条路径
            while (true) {
                // 判断是否可扩张团(即第k个顶点是否可以加入已有团)
                // 同时计算已有团的大小
                boolean expand = true;
                int cliqueSize = 0;
                for (int i = 0; i < k; i++) {
                    if (vector[i] == 1) {
                        cliqueSize++;
                        if (edges[k][i] == 0) {
                            expand = false;
                        }
                    }
                }

                int costFunctionValue;
                if (expand) {
                    // 可扩张团
                    vector[k] = 1;
                    cliqueSize++;
                    costFunctionValue = cliqueSize + n - (k + 1);
                } else {
                    // 不可扩张团
                    vector[k] = 0;
                    costFunctionValue = cliqueSize + n - (k + 1);
                }

                if (k == n - 1) {
                    // 此时恰好遍历到最后一个顶点 即最后一层
                    if (cliqueSize > upperBoundFunctionValue) {
                        upperBoundFunctionValue = cliqueSize;
                        solutionVector = vector.clone();
                    }
                    break;
                }

                // 判断是否有必要继续深度优先遍历下一个顶点
                if (costFunctionValue > upperBoundFunctionValue) {
                    // 有必要继续深度优先遍历
                    k++;
                } else {
                    // 无必要继续深度优先遍历
                    break;
                }
            }

            if (k == 0) {
                // case1 即在第0个顶点对应的结点被剪枝
                // case2 顶点只有一个

                // 整棵树已遍历完毕
                return;
            } else {
                // case1 本次遍历未到达最后一个顶点 即在第k个顶点对应的结点被剪枝 (0 < k < n-1)
                // case2 本次遍历已到达最后一个顶点 (k = n - 1)

                // 对case1和case2 无需考虑第k个(最后一个)顶点是否在团中
                k--;
                while ((k > 0) && (vector[k] == 0)) {
                    // 回溯到父节点
                    k--;
                }

                if (vector[k] == 1) {
                    // 搜索右分支
                    vector[k] = 0;
                    k++;
                } else {
                    // 此时vector[k] = 0 且 k = 0
                    // 整棵树已遍历完毕
                    return;
                }
            }
        }
    }

}
