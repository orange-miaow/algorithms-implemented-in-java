package org.example.dynamic_programming;

public class MatrixChain {

    public static int recursive(int[] vectorP, int i, int j) {
        if (i == j) {
            return 0;
        }

        int mIJ = -1;
        int sIJ = i;

        for (int k = i; k < j; k++) {
            int mIKJ = recursive(vectorP, i, k) + recursive(vectorP, k + 1, j) + vectorP[i - 1] * vectorP[k] * vectorP[j];

            if (mIJ == -1 || mIKJ < mIJ) {
                mIJ = mIKJ;
                sIJ = k;
            }
        }


        System.out.printf("m[%s, %s]=%s%n", i, j, mIJ);
        System.out.printf("s[%s, %s]=%s%n", i, j, sIJ);
        System.out.println("----------");

        return mIJ;
    }

}
