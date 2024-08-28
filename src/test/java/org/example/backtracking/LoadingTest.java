package org.example.backtracking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LoadingTest {

    int[] w = new int[]{90, 80, 40, 30, 20, 12, 10};
    int c1 = 152;
    int c2 = 130;

    @Test
    void select() {
//        Arrays.sort(w);
        Loading.Solution solution = Loading.select(w, c1, c2);

        if (null == solution) {
            System.out.printf("NO-无解\n");
        } else {
            System.out.printf("YES-有解\n");
            System.out.printf("解向量: %s\n", Arrays.toString(solution.x));

            List<Integer> ship1Loads = new ArrayList<>();
            List<Integer> ship2Loads = new ArrayList<>();

            for (int i = 0; i < w.length; i++) {
                if (solution.x[i] == 1) {
                    ship1Loads.add(w[i]);
                } else {
                    ship2Loads.add(w[i]);
                }
            }

            System.out.printf("第一条船负载: %s\n", ship1Loads);
            System.out.printf("第二条船负载: %s\n", ship2Loads);
        }
    }
}