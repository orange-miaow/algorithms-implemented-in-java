package org.example.dynamic_programming;

import org.junit.jupiter.api.Test;

class OptimalBinarySearchTreeTest {

    int[] S = new int[]{0, 'A', 'B', 'C', 'D', 'E'};
    float[] a = new float[]{0.04f, 0.02f, 0.02f, 0.05f, 0.06f, 0.01f};
    float[] b = new float[]{0, 0.1f, 0.3f, 0.1f, 0.2f, 0.1f};
    float[][] m = new float[S.length][S.length];
    int[][] s = new int[S.length][S.length];
    @Test
    void iterative() {
        OptimalBinarySearchTree.iterative(S, a, b, m, s);
        System.out.printf("m[%s][%s]=%s\n", 1, S.length - 1, m[1][S.length - 1]);
        System.out.printf("s[%s][%s]=%s\n", 1, S.length - 1, s[1][S.length - 1]);

        OptimalBinarySearchTree.retrospect(s, 1, S.length - 1);
    }
}