package org.example.dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SequenceAlignment {

    /**
     *
     * @param S1
     * 字符序列  x1 x2 . . . xn
     * @param S2
     * 字符序列 y1 y2 . . . ym
     * @param C
     * 备忘录
     * [i][j] 存放  min cost of aligning strings x1 x2 . . . xi and y1 y2 . . . Yj.
     * @param s
     * 标记函数
     * [i][j] 存放 S1[1..i]与S2[1..j]的子问题的划分方向
     * y,x →
     * ↓
     */
    public static void iterative(String[] S1, String[] S2, int[][] C, String[][] s) {
        int n = S1.length - 1;
        int m = S2.length - 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) { // S1[1..i]与S2[1..j]的子问题
                // Case 1a: OPT matches xi-yj and xi is equal to yj
                // - min cost of aligning two strings x1 x2 . . . xi-1 and y1 y2 . . . yj-1
                // Case 1b: OPT matches xi-yj and xi is not equal to yj
                // - pay mismatch for xi-yj + min cost of aligning two strings x1 x2 . . . xi-1 and y1 y2 . . . yj-1
                // Case 2a: OPT leaves xi unmatched.
                // – pay gap for xi and min cost of aligning x1 x2 . . . xi-1 and y1 y2 . . . yj
                // Case 2b: OPT leaves yj unmatched.
                // pay gap for yj and min cost of aligning x1 x2 . . . xi and y1 y2 . . . yj-1

                if (S1[i].equals(S2[j])) {
                    C[i][j] = C[i - 1][j - 1]; // Case 1a
                    s[i][j] = "↖";
                } else {
                    C[i][j] = 1 + C[i - 1][j - 1]; // Case 1b
                    s[i][j] = "↖";
                }

                if (C[i][j] > 1 + C[i - 1][j]) {
                    C[i][j] = 1 + C[i - 1][j]; // Case 2a
                    s[i][j] = "←";
                }
                if (C[i][j] > 1 + C[i][j - 1]) {
                    C[i][j] = 1 + C[i][j - 1]; // Case 2b
                    s[i][j] = "↑";
                }
            }
        }

    }

    // 追溯
    public static void retrospect(String[] S1, String[] S2, String[][] s) {
        int i = S1.length - 1;
        int j = S2.length - 1;

        List<String> sequence1 = new ArrayList<>(S1.length);
        sequence1.addAll(Arrays.asList(S1));
        List<String> sequence2 = new ArrayList<>(S2.length);
        sequence2.addAll(Arrays.asList(S2));

        while (i >= 1 && j >= 1) {
            String mark = s[i][j];

            if ("↖".equals(mark)) {
                System.out.printf("S1[%s]=%s S2[%s]=%s %s\n", i, S1[i], j, S2[j], S1[i].equals(S2[j]) ? "" : "mismatch");
                System.out.printf("S1=%s\n", sequence1);
                System.out.printf("S2=%s\n", sequence2);
                System.out.println("----------");

                i--;
                j--;
            } else if ("←".equals(mark)) {
                sequence2.add((j == sequence2.size() - 1 ? sequence2.size() : j + 1), "-");
                System.out.printf("在 S2[%s]=%s后添加空格 使该空格与S1[%s]=%s对齐\n", j, S2[j], i, S1[i]);
                System.out.printf("S1=%s\n", sequence1);
                System.out.printf("S2=%s\n", sequence2);
                System.out.println("----------");

                i--;
            } else {
                sequence1.add((i == sequence1.size() - 1 ? sequence1.size() : i + 1), "-");
                System.out.printf("在 S1[%s]=%s后添加空格 使该空格与S2[%s]=%s对齐\n", i, S1[i], j, S2[j]);
                System.out.printf("S1=%s\n", sequence1);
                System.out.printf("S2=%s\n", sequence2);
                System.out.println("----------");

                j--;
            }
        }

        if (i == 0) {
            while (j >= 1) {
                sequence1.add(1, "-");
                System.out.printf("在 S1首元素前添加空格\n");
                System.out.printf("S1=%s\n", sequence1);
                System.out.printf("S2=%s\n", sequence2);
                System.out.println("----------");
                j--;
            }
        } else {
            while (i >= 1) {
                sequence2.add(1, "-");
                System.out.printf("在 S2首元素前添加空格\n");
                System.out.printf("S1=%s\n", sequence1);
                System.out.printf("S2=%s\n", sequence2);
                System.out.println("----------");
                i--;
            }
        }
    }

}
