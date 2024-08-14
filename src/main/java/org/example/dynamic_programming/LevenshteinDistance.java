package org.example.dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LevenshteinDistance {

    /**
     *
     * @param S1
     * 字符序列
     * @param S2
     * 字符序列
     * @param C
     * 备忘录
     * [i][j] 存放 S1[1..i]与S2[1..j]的编辑距离
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
            for (int j = 1; j <= m; j++) {
                if (S1[i].equals(S2[j])) {
                    C[i][j] = C[i - 1][j - 1];
                    s[i][j] = "↖";
                } else {
                    C[i][j] = 1 + C[i - 1][j - 1]; // 替换S1[i]为S2[j]
                    s[i][j] = "x";
                }

                if (1 + C[i - 1][j] < C[i][j]) { // 删除S1[i]
                    C[i][j] = 1 + C[i - 1][j];
                    s[i][j] = "←";
                }
                if (1 + C[i][j - 1] < C[i][j]) { // 在S1[i]后添加S2[j]
                    C[i][j] = 1 + C[i][j - 1];
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

        while (i >=1 && j >= 1 ) {
            String mark = s[i][j];
            if ("↖".equals(mark)) {
                i--;
                j--;
            } else if ("x".equals(mark)) {
                sequence1.set(i, S2[j]);
                System.out.printf("替换 S1[%s]=%s为S2[%s]=%s\n", i, S1[i], j, S2[j]);
                System.out.printf("S1=%s\n", sequence1);

                i--;
                j--;
            } else if ("←".equals(mark)) {
                sequence1.remove(i);
                System.out.printf("删除 S1[%s]=%s\n", i, S1[i]);
                System.out.printf("S1=%s\n", sequence1);

                i--;
            } else {
                if (i == sequence1.size() - 1) {
                    sequence1.add(S2[j]);
                } else {
                    sequence1.add(i + 1, S2[j]);
                }
                System.out.printf("在 S1[%s]=%s后添加S2[%s]=%s\n", i, S1[i], j, S2[j]);
                System.out.printf("S1=%s\n", sequence1);

                j--;
            }
        }

        if (i == 0) {
            while (j >= 1) {
                sequence1.add(1, S2[j]);
                System.out.printf("在 S1首元素前添加S2[%s]=%s\n", j, S2[j]);
                System.out.printf("S1=%s\n", sequence1);
                j--;
            }
        } else {
            while (i >= 1) {
                sequence1.remove(i);
                System.out.printf("删除 S1[%s]=%s\n", i, S1[i]);
                System.out.printf("S1=%s\n", sequence1);
                i--;
            }
        }

    }
}
