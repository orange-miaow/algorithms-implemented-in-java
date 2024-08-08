package org.example.dynamic_programming;

import java.util.Stack;
import java.util.StringJoiner;

public class LCS {

    /**
     *
     * @param X
     * 序列[1..m]
     * @param Y
     * 序列[1..n]
     * @param C
     * 备忘录
     * [i][j] 存放 序列[1..i]与序列[1..j]的最长公共子序列的长度
     * @param B
     * 标记函数
     * [i][j] 存放 序列[1..i]与序列[1..j]的最长公共子序列的子问题的方向
     *               Y
     *     + + + + + +
     *   +
     *   +
     *   +
     *   +
     *   +
     *   +
     * X +
     */
    public static void iterative(String[] X, String[] Y, int[][] C, String[][] B) {
        int m = X.length - 1;
        int n = Y.length - 1;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X[i].equals(Y[j])) {
                    C[i][j] = 1 + C[i - 1][j - 1];
                    B[i][j] = "↖";
                } else if (C[i - 1][j] >= C[i][j - 1]) {
                    C[i][j] = C[i - 1][j];
                    B[i][j] = "↑";
                } else {
                    C[i][j] = C[i][j - 1];
                    B[i][j] = "←";
                }
            }
        }
    }

    // 追溯
    public static void retrospect(String[] X, String[] Y, String[][] B) {
        int m = X.length - 1;
        int n = Y.length - 1;

        Stack<String> stack = new Stack<>();

        while (m != 0 && n != 0) { // 等价条件 B[m][n] != null
            String direction = B[m][n];
            if ("↖".equals(direction)) {
                stack.add(X[m]);
                m--;
                n--;
            } else if ("↑".equals(direction)) {
                m--;
            } else {
                n--;
            }
        }

        StringJoiner stringJoiner = new StringJoiner(", ");
        while (!stack.isEmpty()) {
            stringJoiner.add(stack.pop());
        }

        System.out.printf("LCS: <%s>", stringJoiner);
    }
}
