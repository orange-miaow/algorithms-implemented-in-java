package org.example.dynamic_programming;

import org.junit.jupiter.api.Test;

class SequenceAlignmentTest {

//    String[] S1 = new String[]{null, "C", "T", "A", "C", "C", "G"};
//    String[] S2 = new String[]{null, "T", "A", "C", "A", "T", "G"};
//    String[] S1 = new String[]{null, "o", "c", "u", "r", "r", "a", "n", "c", "e"};
//    String[] S2 = new String[]{null, "o", "c", "c", "u", "r", "r", "e", "n", "c", "e"};
    String[] S1 = new String[]{null, "a", "x", "a", "b", "c", "d", "e", "s"};
    String[] S2 = new String[]{null, "a", "x", "b", "a", "c", "f", "e", "s"};

    int[][] C = new int[S1.length][S2.length];
    {
        for (int i = 1; i <= S1.length - 1; i++) {
            C[i][0] = i;
        }
        for (int j = 1; j <= S2.length - 1; j++) {
            C[0][j] = j;
        }
    }
    String[][] s = new String[S1.length][S2.length];


    @Test
    void iterative() {
        SequenceAlignment.iterative(S1, S2, C, s);
        System.out.printf("C[%s][%s]=%s\n", S1.length - 1, S2.length - 1, C[S1.length - 1][S2.length - 1]);
        System.out.printf("s[%s][%s]=%s\n", S1.length - 1, S2.length - 1, s[S1.length - 1][S2.length - 1]);
        SequenceAlignment.retrospect(S1, S2, s);
    }

}