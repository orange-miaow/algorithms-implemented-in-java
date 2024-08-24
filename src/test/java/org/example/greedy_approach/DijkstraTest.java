package org.example.greedy_approach;

import org.junit.jupiter.api.Test;

class DijkstraTest {

    int n = 6;
    int[][] w = new int[n + 1][n + 1];

    {
        w[1][2] = 10;
        w[1][6] = 3;
        w[2][3] = 7;
        w[2][4] = 5;
        w[4][1] = 3;
        w[4][3] = 4;
        w[4][5] = 7;
        w[6][2] = 2;
        w[6][4] = 6;
        w[6][5] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (w[i][j] == 0) {
                    w[i][j] = -1;
                }
            }
        }
    }

    int source = 1;

    @Test
    void singleSourceShortestPath() {
        int[] prev = Dijkstra.singleSourceShortestPath(n, w, source);

        for (int j = 1; j <= n; j++) {
            Dijkstra.printShortestPath(source, j, prev);
        }
    }

}