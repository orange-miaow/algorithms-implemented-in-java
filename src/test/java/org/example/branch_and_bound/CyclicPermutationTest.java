package org.example.branch_and_bound;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class CyclicPermutationTest {

    int[] r = new int[]{1, 1, 2, 2, 3, 5};

    @Test
    void searchOptimalSolution() {
        CyclicPermutation cyclicPermutation = new CyclicPermutation(r);
        cyclicPermutation.searchOptimalSolution(0, new int[r.length], 0);

        double lowerBoundFunctionValue = cyclicPermutation.getLowerBoundFunctionValue();
        int[] solutionVector = cyclicPermutation.getSolutionVector();
        System.out.printf("最小排列长度: %s\n", lowerBoundFunctionValue);
        System.out.printf("最优解向量: %s\n", Arrays.toString(solutionVector));
    }
}