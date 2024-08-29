package org.example.branch_and_bound;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class KnapsackTest {

    int B = 10;
    int[] w = new int[]{2, 3, 4, 7};
    int[] v = new int[]{1, 3, 5, 9};

    @Test
    void searchOptimalSolution() {
        Knapsack knapsack = new Knapsack(B, v, w);
        knapsack.searchOptimalSolution(0, new int[v.length]);

        int upperBoundFunctionValue = knapsack.getUpperBoundFunctionValue();
        int[] solutionVector = knapsack.getSolutionVector();
        System.out.printf("最大价值: %s\n", upperBoundFunctionValue);
        System.out.printf("最优解向量: %s\n", Arrays.toString(solutionVector));

        Knapsack.Thing[] things = knapsack.getThings();
        System.out.printf("id\t价值\t重量\t个数\n");
        for (int i = 0; i < v.length; i++) {
            Knapsack.Thing thing = things[i];
            System.out.printf("%s\t%s\t%s\t%s\n", thing.id, thing.value, thing.weight, solutionVector[i]);
        }
    }
}