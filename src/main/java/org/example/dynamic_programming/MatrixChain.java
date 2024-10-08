package org.example.dynamic_programming;

public class MatrixChain {

    public static int recursive(int[] vectorP, int[][] m, int[][] s, int i, int j) {
        if (i == j) {
            return 0;
        }

        int mIJ = -1;
        int sIJ = i;

        for (int k = i; k < j; k++) {
            int mIKJ = recursive(vectorP, m, s, i, k) + recursive(vectorP, m, s, k + 1, j) + vectorP[i - 1] * vectorP[k] * vectorP[j];

            if (mIJ == -1 || mIKJ < mIJ) {
                mIJ = mIKJ;
                sIJ = k;
            }
        }

        m[i][j] = mIJ;
        s[i][j] = sIJ;
        System.out.printf("m[%s, %s]=%s%n", i, j, m[i][j]);
        System.out.printf("s[%s, %s]=%s%n", i, j, s[i][j]);
        System.out.println("----------");

        return m[i][j];
    }

    public static int iterative(int[] vectorP, int[][] m, int[][] s, int n) {
        for (int r = 2; r <= n; r++) { // r个矩阵连乘的子问题
            for (int i = 1; i <= n - r + 1; i++) { // Ai * Ai+1 * ... * Ai+r-1
                int j = i + r - 1; // Ai * Ai+1 * ... * Aj

                m[i][j] = m[i][i] + m[i + 1][j] + vectorP[i - 1] * vectorP[i] * vectorP[j];
                s[i][j] = i;

                for (int k = i + 1; k < j; k++) { // (Ai * Ai+1 * ... * Ak) * (Ak+1 * Ak+2 * ... * Aj)
                    int mIKJ = m[i][k] + m[k + 1][j] + vectorP[i - 1] * vectorP[k] * vectorP[j];
                    if (mIKJ < m[i][j]) {
                        m[i][j] = mIKJ;
                        s[i][j] = k;
                    }
                }
            }
        }

        System.out.printf("m[%s, %s]=%s%n", 1, n, m[1][n]);
        System.out.printf("s[%s, %s]=%s%n", 1, n, s[1][n]);
        System.out.println("----------");

        return m[1][n];
    }

    /**
     * 加括号
     *
     * @param matrixBrackets [矩阵][0] 存放 本矩阵左边的括号
     *                       [矩阵][1] 存放 本矩阵右边的括号
     * @param s
     * @param i
     * @param j
     */
    public static void bracket(String[][] matrixBrackets, int[][] s, int i, int j) {
        int k = s[i][j];

        if (k != i) {
            matrixBrackets[i][0] += "(";
            matrixBrackets[k][1] += ")";
            bracket(matrixBrackets, s, i, k);
        }

        if (k + 1 != j) {
            matrixBrackets[k + 1][0] += "(";
            matrixBrackets[j][1] += ")";
            bracket(matrixBrackets, s, k + 1, j);
        }
    }

    // 生成加过括号的表达式
    public static String express(String[][] matrixBrackets) {
        StringBuilder expression = new StringBuilder();
        for (int t = 1; t < matrixBrackets.length; t++) {
            expression.append(matrixBrackets[t][0]);
            expression.append("A" + t);
            expression.append(matrixBrackets[t][1]);
        }

        return expression.toString();
    }

}
