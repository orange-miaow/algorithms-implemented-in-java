package org.example.dynamic_programming;

import org.junit.jupiter.api.Test;

class MinStampNumTest {

    //    int[] x = new int[]{0, 1, 4, 6, 7};
    //    int[] x = new int[]{0, 1, 2, 3, 4};
    int[] x = new int[]{0, 1, 3, 11, 15, 32};

    @Test
    void iterative() {
        int n = x.length - 1;
        int postage = 70;

        int[][] y = new int[n + 1][postage + 1];
        int[][] s = new int[n + 1][postage + 1];

        MinStampNum.iterative(x, postage, y, s);
        System.out.printf("最少邮票数: %s\n", y[n][postage]);
        MinStampNum.retrospect(x, postage, s);
    }
}