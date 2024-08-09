package org.example.dynamic_programming;

import java.util.Stack;
import java.util.StringJoiner;

public class ImageCompression {

    /**
     *
     * @param P
     * <p1, p2, ..., pn> n个像素的灰度值序列
     * 0 <= pi <= 255
     * @param S
     * 备忘录
     * [i] 存放 存储i个像素的灰度值的最小位数
     * @param l
     * 标记函数
     * [i] 存放 存储i个像素的灰度值的位数最小时的 最后一段的像素个数
     */
    public static void iterative(int[] P, int[] S, int[] l) {
        int header = 8 + 3;

        int n = P.length - 1;

        for (int i = 1; i <= n; i++) {
            int maxBitLength = bitLength(P[i]);
            S[i] = S[i - 1] + maxBitLength * 1;
            l[i] = 1;

            for (int j = 2; j <= Math.min(i, 256); j++) { // <p1, ..., pi-j> [pi-j+1, ..., pi]
                int currentMaxBitLength = bitLength(P[i - j + 1]);
                if (currentMaxBitLength > maxBitLength) {
                    maxBitLength = currentMaxBitLength;
                }

                int currentBitCount = S[i - j] + maxBitLength * j;
                if (currentBitCount < S[i]) {
                    S[i] = currentBitCount;
                    l[i] = j;
                }
            }

            S[i] += header;
        }

    }

    // 追溯
    public static void retrospect(int[] P, int[] l) {
        int i = P.length - 1;

        Stack<String> stack = new Stack<>();
        while (i > 0) {
            int j = l[i]; // 最后一段的像素个数

            StringJoiner stringJoiner = new StringJoiner(", ");
            for (int k = i - j + 1; k <= i; k++) {
                stringJoiner.add("" + P[k]);
            }
            stack.push(stringJoiner.toString());

            i -= j;
        }

        System.out.printf("像素段: \n");
        while (!stack.isEmpty()) {
            System.out.printf("<%s>\n", stack.pop());
        }
    }

    // 求p的二进制位数
    private static int bitLength(int p) {
        int bitLength = 0;
        while (true) {
            p = p >> 1;
            bitLength++;

            if (p == 0) {
                break;
            }
        }

        return bitLength;
    }

}
