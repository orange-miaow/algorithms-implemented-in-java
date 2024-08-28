package org.example.backtracking;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class GraphColoringTest {

    int m = 3;
    int n = 7;
    int[][] edges = new int[n][n];

    {
        edges[0][1] = 1;
        edges[0][5] = 1;
        edges[0][6] = 1;
        edges[1][2] = 1;
        edges[1][6] = 1;
        edges[2][3] = 1;
        edges[2][6] = 1;
        edges[3][4] = 1;
        edges[3][5] = 1;
        edges[4][5] = 1;
        edges[5][6] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (edges[i][j] == 1) {
                    edges[j][i] = 1;
                }
            }
        }
    }

    @Test
    void searchPossibleSolutions() {
        GraphColoring graphColoring = new GraphColoring(m, n, edges);
        graphColoring.searchPossibleSolutions(0, new int[n]);
        List<int[]> possibleSolutionVectors = graphColoring.getPossibleSolutionVectors();

        System.out.printf("可行解个数: %s\n", possibleSolutionVectors.size());
        System.out.printf("可行解向量: \n");
        for (int[] possibleSolutionVector : possibleSolutionVectors) {
            System.out.printf("%s\n", Arrays.toString(possibleSolutionVector));
        }
    }
}