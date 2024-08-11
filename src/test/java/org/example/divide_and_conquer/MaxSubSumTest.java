package org.example.divide_and_conquer;

import org.junit.jupiter.api.Test;

class MaxSubSumTest {

    int[] A = new int[]{0, -2, 11, -4, 13, -5, -2};
    int left = 1;
    int right = A.length - 1;

    @Test
    void find() {
        int[] array = MaxSubSum.find(A, left, right);
        System.out.printf("MaxSubSum=%s, left=%s, right=%s\n", array[0], array[1], array[2]);
        if (array[0] != 0) {
            for (int i = array[1]; i <= array[2] ; i++) {
                System.out.printf("A[%s]=%s\n", i, A[i]);
            }
        }
    }
}