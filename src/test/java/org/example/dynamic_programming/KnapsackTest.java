package org.example.dynamic_programming;

import org.junit.jupiter.api.Test;

class KnapsackTest {

    int n = 4;
    int b = 10;
    int[] w = new int[]{0, 2, 3, 4, 7};
    int[] v = new int[]{0, 1, 3, 5, 9};
    int[][] F = new int[n + 1][b + 1];
    {
        for (int j = 0; j < F.length; j++) {
            F[j][0] = 0;
        }

        for (int j = 0; j < F[0].length; j++) {
            F[0][j] = 0;
        }

        for (int j = 1; j < F[0].length; j++) {
            F[1][j] = (int) Math.floor((double) j / w[1]) * v[1];
        }

    }
    int[][] i = new int[n + 1][b + 1];
    {
        for (int j = 1; j < i[0].length; j++) {
            i[1][j] = (j / w[1] == 0 ? 0 : 1);
        }
    }


    @Test
    void iterative() {
        Knapsack.iterative(n, b, w, v, F, i);
        System.out.printf("F[%s][%s]=%s\n", n, b, F[n][b]);
        System.out.printf("i[%s][%s]=%s\n", n, b, i[n][b]);

        Knapsack.retrospect(n, b, w, i);

    }
}