package org.example.dynamic_programming;

import org.junit.jupiter.api.Test;

class MaxSubSumTest {

    int[] A1 = new int[]{0, -2, 11, -4, 13, -5, -2};
    int[] A2 = new int[]{0, 2, -5, 8, 11, -3, 4, 6};

    @Test
    void find() {
        int[] array = MaxSubSum.find(A2);
        int begin = MaxSubSum.findBegin(A2, array[0], array[1]);
        System.out.printf("MaxSubSum=%s, left=%s, right=%s\n", array[0], begin, array[1]);
        for (int i = begin; i <= array[1] ; i++) {
            System.out.printf("A[%s]=%s\n", i, A2[i]);
        }
    }
}