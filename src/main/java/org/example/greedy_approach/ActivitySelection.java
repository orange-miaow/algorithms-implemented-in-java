package org.example.greedy_approach;

import java.util.ArrayList;
import java.util.List;

public class ActivitySelection {

    /**
     * 求活动集S={1,2,...,n}的最大的两两相容的活动子集A
     * @param n
     * 活动个数
     * @param s
     * s[i] 存放 活动i 的 开始时间
     * @param f
     * f[i]存放 活动i 的 截止时间
     */
    public static List<Integer> select(int n, int[] s, int[] f) {
        List<Integer> A = new ArrayList<>();

        int j = 1;
        A.add(j);

        for (int i = 2; i <= n; i++) {
            if (s[i] >= f[j]) {
                j = i;
                A.add(j);
            }
        }

        return A;
    }

}
