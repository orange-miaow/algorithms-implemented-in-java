package org.example.backtracking;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class ZeroOneKnapsackTest {

    int k = 4;
    int B = 13;
    int[] w = new int[]{8, 6, 4, 3};
    int[] v = new int[]{12, 11, 9, 8};

    @Test
    void getOptimalSolution() {
        ZeroOneKnapsack zeroOneKnapsack = new ZeroOneKnapsack(k, B, w);
        zeroOneKnapsack.searchPossibleSolutions(0, new int[k]);
        List<int[]> possibleSolutionVectors = zeroOneKnapsack.getPossibleSolutionVectors();

        System.out.printf("可行解个数: %s\n", possibleSolutionVectors.size());
        System.out.printf("可行解向量: \n");
        for (int[] possibleSolutionVector : possibleSolutionVectors) {
            System.out.printf("%s\n", Arrays.toString(possibleSolutionVector));
        }

        int[] optimalSolution = ZeroOneKnapsack.getOptimalSolution(v, possibleSolutionVectors);
        System.out.printf("最优解向量: %s\n", Arrays.toString(optimalSolution));
    }
}