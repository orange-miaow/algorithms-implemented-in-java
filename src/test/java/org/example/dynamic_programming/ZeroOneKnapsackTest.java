package org.example.dynamic_programming;

import org.junit.jupiter.api.Test;

class ZeroOneKnapsackTest {

    int n = 4;
    int b = 13;
    int[] w = new int[]{0, 8, 6, 4, 3};
    int[] v = new int[]{0, 12, 11, 9, 8};
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
        ZeroOneKnapsack.iterative(n, b, w, v, F, i);
        System.out.printf("F[%s][%s]=%s\n", n, b, F[n][b]);
        System.out.printf("i[%s][%s]=%s\n", n, b, i[n][b]);

        ZeroOneKnapsack.retrospect(n, b, w, i);
    }
}