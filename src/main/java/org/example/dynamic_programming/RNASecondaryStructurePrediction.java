package org.example.dynamic_programming;

public class RNASecondaryStructurePrediction {

    /**
     *
     * @param S
     * 由字符A C G U构成的长为n的链
     * @param C
     * 备忘录
     * [i][j] 存放 序列S[i..j]的最大匹配数目
     * @param s
     * [i][j] 存放 序列S[i..j]的匹配数目最大时的子问题的划分
     */
    public static void iterative(int[] S, int[][] C, int[][] s) {
        int n = S.length - 1;
        for (int r = 5; r <= n; r++) {
            for (int i = 1; i <= n - r + 1; i++) {
                int j = i + r - 1;

                C[i][j] = C[i][j - 1]; // j不参与配对
                s[i][j] = -1;

                for (int k = i; k <= j - 4; k++) {
                    if (paired(S[k], S[j])) { // j与k配对
                        int mIJ = 0;
                        if (k == i) {
                            mIJ = 1 + C[k + 1][j - 1];
                        } else {
                            mIJ = 1 + C[i][k - 1] + C[k + 1][j - 1];
                        }

                        if (mIJ > C[i][j]) {
                            C[i][j] = mIJ;
                            s[i][j] = k;
                        }
                    }
                }
            }
        }
    }

    /// 判断是否配对
    private static boolean paired(int x, int y) {
        if (x == 'U' && y == 'A') {
            return true;
        } else if (x == 'A' && y == 'U') {
            return true;
        } else if (x == 'C' && y == 'G') {
            return true;
        } else if (x == 'G' && y == 'C') {
            return true;
        }

        return false;
    }

}
