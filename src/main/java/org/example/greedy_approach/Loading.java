package org.example.greedy_approach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Loading {

    /**
     * 选择一些集装箱 使得装上船的集装箱个数最多
     * @param n
     * 集装箱个数
     * @param w
     * [i] 集装箱i的重量
     * @param C
     * 轮船最多装载量
     */
    public static List<Integer> select(int n, int[] w, int C) {
        List<Integer> selected = new ArrayList<>();

        // 对集装箱按重量排序 使得 w1<=w2<=...<=wn
        Arrays.sort(w);

        int W = w[1];
        selected.add(w[1]);

        for (int j = 2; j <= n; j++) {
            if (W + w[j] <= C) {
                W += w[j];
                selected.add(w[j]);
            } else {
                break;
            }
        }

        return selected;
    }

}
