package org.example.dynamic_programming;

import org.junit.jupiter.api.Test;

class LevenshteinDistanceTest {

//    String[] S1 = new String[]{"", "h", "o", "r", "s", "e"};
//    String[] S2 = new String[]{"", "r", "o", "s"};

    String[] S1 = new String[]{"", "v", "i", "n", "t", "n", "e", "r"};
    String[] S2 = new String[]{"", "w", "r", "i", "t", "e", "r", "s"};

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
        LevenshteinDistance.iterative(S1, S2, C, s);
        System.out.printf("C[%s][%s]=%s\n", S1.length - 1, S2.length - 1, C[S1.length - 1][S2.length - 1]);
        System.out.printf("s[%s][%s]=%s\n", S1.length - 1, S2.length - 1, s[S1.length - 1][S2.length - 1]);
        LevenshteinDistance.retrospect(S1, S2, s);
    }
}