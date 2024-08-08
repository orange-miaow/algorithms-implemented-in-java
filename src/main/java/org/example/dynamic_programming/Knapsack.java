package org.example.dynamic_programming;

public class Knapsack {

    /**
     * @param n
     * 物品的种类数
     * @param b
     * 最大重量限制
     * @param w
     * [物品] 存放 本物品的重量
     * @param v
     * [物品] 存放 本物品的价值
     * @param F
     * 备忘录
     * [k][y] 在最大重量限制y下 放入前k种物品 所产生的最大价值
     * @param i
     * 标记函数
     * [k][y] 在最大重量限制y下 放入前k种物品 产生的最大价值时 最大的物品序号
     */
    public static void iterative(int n, int b, int[] w, int[] v, int[][] F, int[][] i) {
        for (int k = 2; k <= n; k++) { // 放入前n种物品
            for (int y = 1; y <= b; y++) { // 放入前n种物品的最大重量限制
                int mv0 = F[k - 1][y]; // 在最大重量限制y下 不放入第k种物品 放入前k-1种物品 所产生的最大价值

                if (y - w[k] < 0) {
                    F[k][y] = mv0;
                    i[k][y] = i[k - 1][y];
                    continue;
                }

                int mv1 = v[k] + F[k][y - w[k]]; // 在最大重量限制y下 放入前n种物品(第k种物品至少放入1个) 所产生的最大价值

                if (mv0 > mv1) {
                    F[k][y] = mv0;
                    i[k][y] = i[k - 1][y];
                } else {
                    F[k][y] = mv1;
                    i[k][y] = k;
                }
            }
        }
    }

    // 追溯
    public static void retrospect(int n, int b, int[] w, int[][] i) {
        int[] x = new int[n + 1];

        while (i[n][b] > 0) {
            int k = i[n][b];
            x[k]++;

            n = k;
            b -= w[k];
        }

        for (int j = 1; j < x.length; j++) {
            System.out.printf("物品: %s\t数量: %s\n", j, x[j]);
        }
    }

}
