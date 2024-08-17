package org.example.greedy_approach;

import org.example.DataGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class LoadingTest {

    @Test
    void select() {
        int n = 10;
        int[] w = DataGenerator.generate(n + 1, 10);
        w[0] = -1;
        int C = 10;

        List<Integer> selected = Loading.select(n, w, C);
        System.out.printf("weights: %s\n", Arrays.toString(w));
        System.out.printf("selected: %s", selected);
    }

}