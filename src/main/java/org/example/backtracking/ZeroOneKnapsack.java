package org.example.backtracking;

import java.util.ArrayList;
import java.util.List;

public class ZeroOneKnapsack extends CompleteKaryTreeTemplate {

    // 最大重量限制
    private int B;
    // [i] 物品i的重量
    private int[] w;

    public ZeroOneKnapsack(int n, int b, int[] w) {
        super(2, n);
        B = b;
        this.w = w;
    }

    @Override
    boolean whetherTheConstraintConditionsAreMet(int level, int[] vector) {
        int sum = 0;
        for (int i = 0; i <= level; i++) {
            if (vector[i] == 1) {
                sum += w[i];
            }
        }

        if (sum > B) {
            return false;
        }

        return true;
    }


    /**
     * 获取最佳解向量
     *
     * @param v                       [i] 物品i的价值
     * @param possibleSolutionVectors 可行解向量列表
     */
    public static int[] getOptimalSolution(int[] v, List<int[]> possibleSolutionVectors) {
        List<Integer> vs = new ArrayList<>();

        for (int[] vector : possibleSolutionVectors) {
            int sum = 0;
            for (int i = 0; i < vector.length; i++) {
                if (vector[i] == 1) {
                    sum += v[i];
                }
            }

            vs.add(sum);
        }

        int max = 0;
        int maxIndex = -1;
        for (int i = 0; i < vs.size(); i++) {
            if (vs.get(i) > max) {
                max = vs.get(i);
                maxIndex = i;
            }
        }

        System.out.printf("最大价值: %s\n", max);

        return possibleSolutionVectors.get(maxIndex);
    }

}
