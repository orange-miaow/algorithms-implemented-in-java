package org.example.dynamic_programming;

import org.junit.jupiter.api.Test;

class InvestmentTest {

    private int n = 4;
    private int m = 5;
    private int[][] f = new int[][]{
            {0, 0, 0, 0, 0, 0},
            {0, 11, 12, 13, 14, 15},
            {0, 0, 5, 10, 15, 20},
            {0, 2, 10, 30, 32, 40},
            {0, 20, 21, 22, 23, 24}
    };
    private int[][] F = new int[n + 1][m + 1];
    {
        for (int i = 0; i < F[1].length; i++) {
            F[1][i] = f[1][i];
        }
    }
    private int[][] x = new int[n + 1][m + 1];
    {
        for (int i = 0; i < x[1].length; i++) {
            x[1][i] = 1;
        }
    }
    @Test
    void iterative() {
        Investment.iterative(n, m, f, F, x);
        System.out.printf("F[%s][%s]=%s\n", n, m, F[n][m]);
        System.out.printf("x[%s][%s]=%s\n", n, m, x[n][m]);

        Investment.retrospect(n, m, f, x);
    }
}