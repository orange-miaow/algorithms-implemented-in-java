package org.example.dynamic_programming;

public class MinStampNum {

    /**
     * 求用面值为x1,x2,...,xi的邮票贴j邮资时的最少邮票数
     *
     * @param x       面值X=<x1,x2,...,xn>
     * @param postage 每个信封最多贴上的邮票张数
     * @param y       [i][j] 用面值为x1,x2,...,xi的邮票贴j邮资时的最少邮票数
     * @param s       [i][j] 用面值为x1,x2,...,xi的邮票贴j邮资时的最少邮票数中xi的张数
     */
    public static void iterative(int[] x, int postage, int[][] y, int[][] s) {
        int n = x.length - 1;

        // 初始化
        for (int j = 1; j <= postage; j++) {
            y[1][j] = j;
            s[1][j] = j;
        }
        y[1][1] = 1;
        s[1][1] = 1;
        for (int i = 2; i <= n; i++) {
            y[i][1] = 1;
            s[i][1] = 0;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= postage; j++) {
                y[i][j] = y[i - 1][j]; // 面值为xi的邮票选择了0张
                s[i][j] = 0;

                for (int t = 1; t <= Math.floor((double) j / x[i]); t++) { // 面值为xi的邮票选择了t张
                    int stampNum = t + y[i - 1][j - t * x[i]];
                    if (stampNum < y[i][j]) {
                        y[i][j] = stampNum;
                        s[i][j] = t;
                    }
                }

            }
        }
    }

    // 追溯
    public static void retrospect(int[] x, int postage, int[][] s) {
        int i = x.length - 1;
        int j = postage;

        while (i >= 1) {
            int t = s[i][j];
            System.out.printf("编号: %s\t面值: %s\t张数: %s\t总值: %s\n", i, x[i], t, t * x[i]);
            j -= t * x[i];
            i--;
        }
    }

}
