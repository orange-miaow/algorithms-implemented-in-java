package org.example.dynamic_programming;

public class Investment {

    /**
     *
     * @param n
     * 项目数
     * @param m
     * 总投资额
     * @param f
     * [项目][投资额x] 存放 本项目投资x所产生的收益
     * @param F
     * 备忘录
     * [k][i] 对前k个项目总投资i 所产生的最大总收益
     * @param x
     * 标记函数
     * x[k][i] 对前k个项目总投资i 产生最大总收益时 第k个项目的投资为i
     */
    public static void iterative(int n, int m, int[][] f, int[][] F, int[][] x) {
        for (int k = 2; k <= n; k++) { // 投资前k个项目
            for (int i = 1; i <= m; i++) { // 对前k个项目总投资i
                F[k][i] = f[k][0] + F[k - 1][i]; // 对第k个项目投资0 对前k-1个项目总投资i 所产生的总收益
                x[k][i] = 0;

                for (int j = 1; j <= i; j++) {
                    int earnings = f[k][j] + F[k - 1][i - j]; // 对第k个项目投资j 对前k-1个项目总投资i 所产生的总收益
                    if (earnings > F[k][i]) {
                        F[k][i] = earnings;
                        x[k][i] = j;
                    }
                }
            }
        }
    }

    // 追溯
    public static void retrospect(int n, int m, int[][] f, int[][] x) {
        if (n == 0) {
            return;
        }

        int i = x[n][m];
        System.out.printf("项目: %s\t投资额: %s\t收益: %s\n", n, i, f[n][i]);
        retrospect(n - 1, m - i, f, x);
    }

}
