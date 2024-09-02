package org.example.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Loading {

    /**
     * 尝试将n个集装箱装入两艘船
     *
     * @param w  [i] 集装箱i的重量 i=0,1,2,...,n-1
     * @param c1 第一条船的载重
     * @param c2 第二条船的载重
     */
    public static Solution select(int[] w, int c1, int c2) {
        // (1) 尝试将n个集装箱装入第一条船
        List<Solution> possibleSolutions = loadOntoShip1(w, c1);
        // 对(1)的可行解按空闲空间的从小到大顺序排序 (空闲空间越小 则 在第一条船上装载量越大)
        possibleSolutions.sort((o1, o2) -> {
            if (o1.B < o2.B) {
                return -1;
            } else if (o1.B > o2.B) {
                return 1;
            }
            return 0;
        });
        // 获取第一条船装载量最大的解
        Solution optimalSolution = possibleSolutions.get(0);


        // 判断第二条船是否能装下剩余集装箱
        int remainder = 0;
        for (int i = 0; i < w.length; i++) {
            if (optimalSolution.x[i] == 0) {
                // 即集装箱i未装入第一条船
                remainder += w[i];
            }
        }

        if (remainder <= c2) {
            return optimalSolution;
        } else {
            return null;
        }
    }

    /**
     * 尝试将n个集装箱装入第一条船
     *
     * @param w  [i] 集装箱i的重量 i=0,1,2,...,n-1
     * @param c1 第一条船的载重
     */
    private static List<Solution> loadOntoShip1(int[] w, int c1) {
        List<Solution> possibleSolutions = new ArrayList<>();

        // 集装箱个数
        int n = w.length;
        // [i] 集装箱i第一条船的装入情况 1-装入 0-不装入
        int[] x = new int[n];

        UpDownArgs args = new UpDownArgs(c1, 0);
        while (true) {
            upDown(w, x, args);
            // 此时已经得到一个可行解
            possibleSolutions.add(new Solution(x.clone(), args.B));
            // 此时level=n
            args.level--;

            while ((args.level > 0) && (x[args.level] == 0)) {
                // 回溯到父结点
                args.level--;
            }

            if (x[args.level] == 1) {
                // 搜索右分支
                x[args.level] = 0;
                args.B += w[args.level];
                args.level++;
            } else {
                // 此时x[args.level] = 0 且 args.level = 0
                // 整棵树已遍历完毕
                break;
            }
        }

        return possibleSolutions;
    }

    /**
     * 从第level个集装箱开始 自上向下确定一个第一条船的装载方案 1-装入 0-不装入
     * 从第level层的某个结点开始 自上向下确定一条路径(对应一个可行解) 1-左分支 0-右分支
     *
     * @param w    [i] 集装箱i的重量 i=0,1,2,...,n-1
     * @param x    [i] 集装箱i第一条船的装入情况 1-装入 0-不装入
     * @param args args.B 空闲载重量
     *             args.level 层级 level=0,1,2,...,n-1
     */
    private static void upDown(int[] w, int[] x, UpDownArgs args) {
        // 集装箱个数
        int n = w.length;

        while (args.level <= n - 1) {
            if (w[args.level] <= args.B) {
                // 当前层(即第level个)集装箱可以装入
                x[args.level] = 1;
                args.B -= w[args.level];
            } else {
                // 当前层(即第level个)集装箱不可以装入
                x[args.level] = 0;
            }

            args.level++;
        }
    }

    static class UpDownArgs {
        // 空闲载重量
        public int B;
        // 层级 level=0,1,2,...,n-1
        public int level;

        public UpDownArgs(int b, int level) {
            B = b;
            this.level = level;
        }
    }

    public static class Solution {
        // [i] 集装箱i第一条船的装入情况 1-装入 0-不装入
        public int[] x;
        // 空闲载重量
        public int B;

        public Solution(int[] x, int b) {
            this.x = x;
            B = b;
        }
    }

}
