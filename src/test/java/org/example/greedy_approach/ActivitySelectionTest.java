package org.example.greedy_approach;

import org.junit.jupiter.api.Test;

import java.util.List;

class ActivitySelectionTest {

    int n = 10;
    int[] s = new int[]{-1, 1, 3, 2, 5, 4, 5, 6, 8, 8, 2};
    int[] f = new int[]{-1, 4, 5, 6, 7, 9, 9, 10, 11, 12, 13};

    @Test
    void select() {
        List<Integer> A = ActivitySelection.select(n, s, f);
        System.out.printf("最大的两两相容的活动子集A: %s\n", A);
        System.out.printf("|A|: %s\n", A.size());
    }

}