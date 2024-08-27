package org.example.backtracking;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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

        System.out.printf("");
    }

    @Test
    void getOptimalSolution() {
        TSP tsp = new TSP(n, w);
        tsp.searchPossibleSolutions(0, new int[n - 1]);
        List<int[]> possibleSolutionVectors = tsp.getPossibleSolutionVectors();

        System.out.printf("可行解个数: %s\n", possibleSolutionVectors.size());
        System.out.printf("可行解向量: \n");
        for (int[] possibleSolutionVector : possibleSolutionVectors) {
            System.out.printf("%s\n", Arrays.toString(possibleSolutionVector));
        }

        int[] optimalSolution = TSP.getOptimalSolution(w, possibleSolutionVectors);
        System.out.printf("最优解向量: %s\n", Arrays.toString(optimalSolution));

        System.out.printf("最优路径: 0->");
        for (int i : optimalSolution) {
            System.out.printf("%s->", (i + 1));
        }
        System.out.printf("0");
    }

}