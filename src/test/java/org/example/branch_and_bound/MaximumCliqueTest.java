package org.example.branch_and_bound;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class MaximumCliqueTest {

    int n = 5;
    int[][] edges = new int[n][n];

    {
        edges[0][1] = 1;
        edges[0][2] = 1;
        edges[0][3] = 1;
        edges[0][4] = 1;
        edges[1][3] = 1;
        edges[2][3] = 1;
        edges[2][4] = 1;
        edges[3][4] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (edges[i][j] == 1) {
                    edges[j][i] = 1;
                }
            }
        }
    }

    @Test
    void searchOptimalSolution() {
        MaximumClique maximumClique = new MaximumClique(n, edges);
        maximumClique.searchOptimalSolution();
        int upperBoundFunctionValue = maximumClique.getUpperBoundFunctionValue();
        int[] solutionVector = maximumClique.getSolutionVector();
        System.out.printf("最大团大小: %s\n", upperBoundFunctionValue);
        System.out.printf("最优解向量: %s\n", Arrays.toString(solutionVector));
    }
}