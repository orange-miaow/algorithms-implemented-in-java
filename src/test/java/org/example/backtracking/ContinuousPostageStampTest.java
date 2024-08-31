package org.example.backtracking;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class ContinuousPostageStampTest {

    int n = 4;
    int m = 3;

    @Test
    void searchOptimalSolution() {
        ContinuousPostageStamp continuousPostageStamp = new ContinuousPostageStamp(n, m);
        continuousPostageStamp.searchOptimalSolution(0, new int[n], new int[n]);

        int max = continuousPostageStamp.getMax();
        int[] solutionVector = continuousPostageStamp.getSolutionVector();
        System.out.printf("最大连续邮资区间长度: %s\n", max);
        System.out.printf("最优解向量: %s\n", Arrays.toString(solutionVector));
    }
}