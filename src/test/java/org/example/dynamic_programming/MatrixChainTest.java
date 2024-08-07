package org.example.dynamic_programming;

import org.junit.jupiter.api.Test;

class MatrixChainTest {

    private int[] vectorP = new int[]{30, 35, 15, 5, 10, 20};
    private String[][] matrixBrackets = new String[vectorP.length][2];
    {
        for (int i = 0; i < matrixBrackets.length; i++) {
            for (int j = 0; j < matrixBrackets[i].length; j++) {
                matrixBrackets[i][j] = "";
            }
        }
    }

    @Test
    void recursive() {
        int n = vectorP.length - 1;
        int[][] m = new int[n + 1][n + 1];
        int[][] s = new int[n + 1][n + 1];
        MatrixChain.recursive(vectorP, m, s, 1, n);

        MatrixChain.iterative(vectorP, m, s, n);

        MatrixChain.bracket(matrixBrackets, s, 1, n);
        String expression = MatrixChain.express(matrixBrackets);
        System.out.println("expression: " + expression);
    }
}