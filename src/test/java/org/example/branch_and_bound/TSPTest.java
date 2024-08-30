package org.example.branch_and_bound;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class TSPTest {

    int n = 4;
    int[][] w = new int[n][n];

    {
        w[0][1] = 5;
        w[0][2] = 9;
        w[0][3] = 4;
        w[1][2] = 13;
        w[1][3] = 2;
        w[2][3] = 7;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (w[i][j] > 0) {
                    w[j][i] = w[i][j];
                } else {
                    w[i][j] = -1;
                    w[j][i] = -1;
                }
            }
        }
    }

    @Test
    void searchOptimalSolution() {
        TSP tsp = new TSP(n, w);
        int[] vector = new int[n];
        vector[0] = 0;
        tsp.searchOptimalSolution(1, vector);

        int lowerBoundFunctionValue = tsp.getLowerBoundFunctionValue();
        int[] solutionVector = tsp.getSolutionVector();
        System.out.printf("最短回路长度: %s\n", lowerBoundFunctionValue);
        System.out.printf("最优解向量: %s\n", Arrays.toString(solutionVector));
    }
}