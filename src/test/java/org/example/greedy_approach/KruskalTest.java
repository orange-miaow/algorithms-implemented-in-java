package org.example.greedy_approach;

import org.junit.jupiter.api.Test;

import java.util.List;

class KruskalTest {

    int n = 8;
    int[][][] w = new int[n + 1][n + 1][];

    {
        w[1][2] = new int[]{2};
        w[1][3] = new int[]{3};
        w[2][2] = new int[]{1};
        w[2][3] = new int[]{4};
        w[2][4] = new int[]{18};
        w[2][7] = new int[]{7};
        w[3][8] = new int[]{8};
        w[4][5] = new int[]{5};
        w[4][6] = new int[]{3};
        w[5][6] = new int[]{4};
        w[7][8] = new int[]{1, 2};

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i - 1; j++) {
                w[i][j] = w[j][i];
            }
        }
    }

    @Test
    void minimumSpanningTree() {
        List<int[]> edges = Kruskal.minimumSpanningTree(n, w);
        int sum = 0;
        for (int[] edge : edges) {
            sum += edge[2];
            System.out.printf("边: (%s, %s) \t 权: %s\n", edge[0], edge[1], edge[2]);
        }
        System.out.printf("最小生成树的权: %s\n", sum);
    }
}