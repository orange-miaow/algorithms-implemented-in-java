package org.example.dynamic_programming;

import org.junit.jupiter.api.Test;

class MatrixChainTest {

    private int[] vectorP = new int[]{30, 35, 15, 5, 10, 20};

    @Test
    void recursive() {
        int n = vectorP.length - 1;
        int[][] m = new int[n + 1][n + 1];
        int[][] s = new int[n + 1][n + 1];
        MatrixChain.recursive(vectorP, m, s, 1, 5);

        MatrixChain.iterative(vectorP, m, s, n);
    }
}