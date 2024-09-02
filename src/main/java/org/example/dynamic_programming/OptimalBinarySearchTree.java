package org.example.dynamic_programming;

public class OptimalBinarySearchTree {

    /**
     * @param S S=<x1, x2, ..., xn>
     * @param a [j] 处在第j个空隙结点的概率 (j=0, 1, 2, ..., n)
     * @param b [i] 处在数据结点xi的概率 (i=1, 2, ..., n)
     * @param m 备忘录
     *          [i][j]相对于输入S[i, j]和P[i, j]的最优二分检索树的最少平均比较次数
     * @param s 标记函数
     *          [i][j] 相对于输入S[i, j]和P[i, j]的最优二分检索树的平均比较次数最少时的树根
     */
    public static void iterative(int[] S, float[] a, float[] b, float[][] m, int[][] s) {
        int n = S.length - 1;
        for (int r = 1; r <= n; r++) {
            for (int i = 1; i <= n - r + 1; i++) {
                int j = i + r - 1;

                float wIJ = 0;
                for (int p = i - 1; p <= j; p++) {
                    wIJ += a[p];
                }
                for (int q = i; q <= j; q++) {
                    wIJ += b[q];
                }

                if (i == j) {
                    m[i][j] = wIJ;
                    continue;
                }

                m[i][j] = -1;
                s[i][j] = -1;
                for (int k = i; k <= j; k++) { // 左子树<xi, xi+1, ..., xk-1> 根xk 右子树<xk+1, xk+2, ..., xj>
                    float mIj = 0;

                    if (k == i) { // 没有左子树
                        mIj = m[k + 1][j];
                    } else if (k == j) { // 没有右子树
                        mIj = m[i][k - 1];
                    } else {
                        mIj = m[i][k - 1] + m[k + 1][j];
                    }

                    if (m[i][j] == -1 || m[i][j] > mIj) {
                        m[i][j] = mIj;
                        s[i][j] = k;
                    }
                }
                m[i][j] += wIJ;
            }
        }
    }

    // 追溯
    public static void retrospect(int[][] s, int begin, int end) {
        if (begin >= end) {
            return;
        }

        int root = s[begin][end];
        System.out.printf("s[%s][%s]=%s\n", begin, end, root);

        retrospect(s, begin, root - 1); // 左子树
        retrospect(s, root + 1, end); // 右子树
    }

}
