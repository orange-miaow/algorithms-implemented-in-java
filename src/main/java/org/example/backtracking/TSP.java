package org.example.backtracking;

import java.util.ArrayList;
import java.util.List;

public class TSP extends PermutationTreeTemplate {

    // [i][j] 城市i至城市j的距离 -1表示不可达
    private int[][] w;

    public TSP(int n, int[][] w) {
        super(n - 1);
        this.w = w;
    }

    @Override
    boolean whetherTheConstraintConditionsAreMet(int level, int[] vector) {
        int formerCity;
        int currentCity;

        if (level == 0) {
            formerCity = 0;
        } else {
            formerCity = vector[level - 1] + 1;
        }
        currentCity = vector[level] + 1;

        if (w[formerCity][currentCity] == -1) {
            // 上一个城市与当前城市不可达
            return false;
        } else if ((level == vector.length - 1) && (w[currentCity][0] == -1)) {
            // 最后一个城市与驻地城市不可达
            return false;
        }

        return true;
    }

    /**
     * 获取最佳解向量
     *
     * @param w                       [i][j] 城市i至城市j的距离 -1表示不可达
     * @param possibleSolutionVectors 可行解向量列表
     */
    public static int[] getOptimalSolution(int[][] w, List<int[]> possibleSolutionVectors) {
        List<Integer> ws = new ArrayList<>();

        for (int[] vector : possibleSolutionVectors) {
            int formerCity = 0;

            int sum = 0;
            for (int i = 0; i < vector.length; i++) {
                int currentCity = vector[i] + 1;

                sum += w[formerCity][currentCity];

                formerCity = currentCity;
            }
            sum += w[formerCity][0];

            ws.add(sum);
        }

        int min = -1;
        int minIndex = -1;
        for (int i = 0; i < ws.size(); i++) {
            if ((min == -1) || (ws.get(i) < min)) {
                min = ws.get(i);
                minIndex = i;
            }
        }

        System.out.printf("最小权值: %s\n", min);

        return possibleSolutionVectors.get(minIndex);
    }

}
