package org.example.dynamic_programming;

public class MaxSubSum {

    /**
     *
     * @param A
     * n个整数的序列<a1, a2, ..., an>
     * @return
     * [0] 最大子段和
     * [1] 最大子段和的最后位置
     */
    public static int[] find(int[] A) {
        int n = A.length - 1;

        int b = 0;
        int sum = 0;
        int end = 0;
        for (int i = 1; i <= n; i++) {
            if (b > 0) {
                b += A[i];
            } else {
                b = A[i];
            }

            if (b > sum) {
                sum = b;
                end = i;
            }
        }

        return new int[]{sum, end};
    }

    // 最大子段和的起始位置
    public static int findBegin(int[] A, int sum, int end) {
        while (true) {
            sum -= A[end];

            if (sum == 0) {
                return end;
            }

            end--;
        }
    }

}
