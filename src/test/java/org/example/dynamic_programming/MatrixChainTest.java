package org.example.dynamic_programming;

import org.junit.jupiter.api.Test;

class MatrixChainTest {

    private int[] vectorP = new int[]{30, 35, 15, 5, 10, 20};

    @Test
    void recursive() {
        MatrixChain.recursive(vectorP, 1, 5);
        MatrixChain.iterative(vectorP);
    }
}