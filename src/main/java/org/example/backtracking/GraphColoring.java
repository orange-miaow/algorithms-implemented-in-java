package org.example.backtracking;

public class GraphColoring extends CompleteKaryTreeTemplate {

    // [i][j] 顶点i与顶点j之间是否有边 1有 0无
    private int[][] edges;

    /**
     * @param m     颜色数
     * @param n     顶点数
     * @param edges [i][j] 顶点i与顶点j之间是否有边 1有 0无
     */
    public GraphColoring(int m, int n, int[][] edges) {
        super(m, n);
        this.edges = edges;
    }

    @Override
    boolean whetherTheConstraintConditionsAreMet(int level, int[] vector) {
        // 当前顶点
        int currentVertex = level;
        // 已着色顶点
        int coloredVertex = 0;
        for (int i = 0; i < level; i++) {
            coloredVertex = i;
            if ((edges[currentVertex][coloredVertex] == 1) && (vector[currentVertex] == vector[coloredVertex])) {
                // 当前顶点与已着色顶点 邻接 且 颜色相同
                return false;
            }
        }

        return true;
    }

}
