package org.example.divide_and_conquer;

public class MaxSubSum {

    /**
     *
     * @param A
     * n个整数的序列<a1, a2, ..., an>
     * @param left
     * @param right
     * @return int[]
     * [0] 最大子段和
     * [1] 最大子段和的左边界
     * [2] 最大子段和的右边界
     */
    public static int[] find(int[] A, int left, int right) {
        if (left == right) {
            if (A[left] > 0) {
                return new int[]{A[left], left, right};
            } else {
                return new int[]{0, -1, -1};
            }
        }

        int center = (int) Math.floor((left + right) / 2.0);
        int[] leftMaxSubSegment = find(A, left, center); // 子问题S1
        int[] rightMaxSubSegment = find(A, center + 1, right); // 子问题S2


        int subSegmentSum1 = 0; // 从A[center]向左到A[left]的子段和
        int maxSubSegmentSum1 = 0; // 从A[center]向左到A[left]的最大子段和
        int k1 = center; // 从A[center]向左到A[k1]的子段和 最大
        for (int i = center; i >= left; i--) {
            subSegmentSum1 += A[i];
            if (subSegmentSum1 > maxSubSegmentSum1) {
                maxSubSegmentSum1 = subSegmentSum1;
                k1 = i;
            }
        }

        int subSegmentSum2 = 0; // 从A[center + 1]向右到A[right]的子段和
        int maxSubSegmentSum2 = 0; // 从A[center + 1]向右到A[right]的最大子段和
        int k2 = center + 1; // 从A[center + 1]向右到A[k2]的子段和 最大
        for (int i = center + 1; i <= right; i++) {
            subSegmentSum2 += A[i];
            if (subSegmentSum2 > maxSubSegmentSum2) {
                maxSubSegmentSum2 = subSegmentSum2;
                k2 = i;
            }
        }


        int[] maxSubSegment = new int[]{maxSubSegmentSum1 + maxSubSegmentSum2, k1, k2};
        if (maxSubSegment[0] < leftMaxSubSegment[0]) {
            maxSubSegment = leftMaxSubSegment;
        }
        if (maxSubSegment[0] < rightMaxSubSegment[0]) {
            maxSubSegment = rightMaxSubSegment;
        }

        return maxSubSegment;
    }

}
