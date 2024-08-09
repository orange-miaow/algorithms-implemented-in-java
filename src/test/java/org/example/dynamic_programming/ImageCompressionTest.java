package org.example.dynamic_programming;

import org.junit.jupiter.api.Test;

class ImageCompressionTest {

    int[] P = new int[]{0, 10, 12, 15, 255, 1, 2};
    int[] S = new int[P.length];
    int[] l = new int[P.length];
    @Test
    void iterative() {
        ImageCompression.iterative(P, S, l);
        System.out.printf("S[%s]=%s\n", S.length - 1, S[P.length - 1]);
        System.out.printf("l[%s]=%s\n", l.length - 1, l[P.length - 1]);

        ImageCompression.retrospect(P, l);
    }

}