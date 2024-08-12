package org.example.dynamic_programming;

import org.junit.jupiter.api.Test;

class RNASecondaryStructurePredictionTest {

    int[] S = new int[]{0, 'A', 'C', 'C', 'G', 'C', 'C', 'U', 'A', 'A', 'G', 'C', 'C', 'G', 'U', 'C', 'C', 'U', 'A', 'A', 'G', 'C', 'U', 'T', 'G', 'G', 'A', 'C', 'G',
            'C', 'G', 'C', 'C', 'C', 'C', 'U', 'C', 'U', 'G', 'G', 'G', 'G', 'U', 'U', 'A', 'C', 'C', 'U', 'A', 'A', 'A', 'G', 'U', 'U', 'C', 'A', 'G', 'G', 'U', 'A', 'G',
            'C', 'G', 'G', 'A', 'A', 'U', 'U', 'G', 'G', 'U', 'G', 'A', 'G', 'C', 'C', 'G', 'G', 'U', 'G', 'C', 'A', 'G', 'A', 'U', 'G'
    };
    int[][] C = new int[S.length][S.length];
    int[][] s = new int[S.length][S.length];

    @Test
    void iterative() {
        RNASecondaryStructurePrediction.iterative(S, C, s);
        System.out.printf("C[%s][%s]=%s\n", 1, S.length - 1, C[1][S.length - 1]);
        System.out.printf("s[%s][%s]=%s\n", 1, S.length - 1, s[1][S.length - 1]);
    }
}