package org.example.dynamic_programming;

import org.junit.jupiter.api.Test;

class RNASecondaryStructurePredictionTest {

    int[] S = new int[]{0, 'G', 'A', 'A', 'G', 'G', 'A', 'G', 'U', 'C', 'G', 'U', 'U', 'C', 'A', 'U'};
    int[][] C = new int[S.length][S.length];
    int[][] s = new int[S.length][S.length];
    int[] paired = new int[S.length];
    {
        for (int i = 1; i < paired.length; i++) {
            paired[i] = -1;
        }
    }

    @Test
    void iterative() {
        RNASecondaryStructurePrediction.iterative(S, C, s);
        System.out.printf("C[%s][%s]=%s\n", 1, S.length - 1, C[1][S.length - 1]);
        System.out.printf("s[%s][%s]=%s\n", 1, S.length - 1, s[1][S.length - 1]);

        RNASecondaryStructurePrediction.retrospect(s, paired, 1, S.length - 1);
        System.out.printf("index\tbase\tpaired to\n");
        for (int i = 1; i < S.length; i++) {
            System.out.printf("%s\t%s\t%s\n", i, (char) S[i], paired[i]);
        }
    }
}