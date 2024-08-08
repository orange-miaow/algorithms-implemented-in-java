package org.example.dynamic_programming;

import org.junit.jupiter.api.Test;


class LCSTest {
    String[] X = new String[]{"", "A", "B", "C", "B", "D", "A", "B"};
    String[] Y = new String[]{"", "B", "D", "C", "A", "B", "A"};
    int[][] C = new int[X.length][Y.length];
    {
        for (int i = 0; i < C.length; i++) {
            C[i][0] = 0;
        }
        for (int j = 0; j < C[0].length; j++) {
            C[0][j] = 0;
        }
    }
    String[][] B = new String[X.length][Y.length];

    @Test
    void iterative() {
        LCS.iterative(X, Y, C, B);
        System.out.printf("C[%s][%s]=%s\n", X.length - 1, Y.length - 1, C[X.length - 1][Y.length - 1]);
        System.out.printf("B[%s][%s]=%s\n", X.length - 1, Y.length - 1, B[X.length - 1][Y.length - 1]);

        LCS.retrospect(X, Y, B);
    }
}