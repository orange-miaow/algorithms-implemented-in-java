package org.example.backtracking;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class NQueensTest {

    int n = 8;

    @Test
    void searchPossibleSolutions() {
        NQueens nQueens = new NQueens(n);
        nQueens.searchPossibleSolutions(0, new int[n]);
        List<int[]> possibleSolutionVectors = nQueens.getPossibleSolutionVectors();

        System.out.printf("可行解个数: %s\n", possibleSolutionVectors.size());
        System.out.printf("可行解向量: \n");
        for (int[] possibleSolutionVector : possibleSolutionVectors) {
            System.out.printf("%s\n", Arrays.toString(possibleSolutionVector));
        }
    }
}