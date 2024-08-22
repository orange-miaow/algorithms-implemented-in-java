package org.example.greedy_approach;

import java.util.ArrayList;
import java.util.List;

public class Prim {

    /**
     * @param n 顶点个数
     * @param w [i][j] 第i个顶点到第j个顶点的边 的 权值数组 (i到j的边可能有多条)
     */
    public static List<int[]> minimumSpanningTree(int n, int[][][] w) {
        List<int[]> edges = new ArrayList<>(n - 1);

        // 集合S
        List<Integer> S = new ArrayList<>();

        // near[j]=i  连接V-S集合中的顶点j与S集合的最短边的顶点i
        int[] near = new int[n + 1];

        // d[j]  连接V-S集合中的顶点j与S集合的最短边的权值
        // -1 表示 顶点j已加入到集合S
        // ∞ 表示 V-S集合中的顶点j与S集合不存在边
        int[] d = new int[n + 1];


        // 初始化S
        // 将顶点1加入集合S
        S.add(1);

        // 初始化near和d
        // near[j]=1
        for (int j = 2; j <= n; j++) {
            near[j] = 1;
        }
        // d[1]=-1
        d[1] = -1;
        // d[j]=min(w[1][j][k]) 或 d[j]=∞
        for (int j = 2; j <= n; j++) {
            // 默认不存在1到j的边
            d[j] = Integer.MAX_VALUE;

            int[] ews = w[1][j];
            if (null != ews) {
                for (int ew : ews) {
                    d[j] = Math.min(d[j], ew);
                }
            }
        }


        // 选择连接S与V-S集合的最短边e={i, j} 其中i∈S j∈V-S  将e加入树T j加入S
        while (S.size() != n) {
            // 在V-S集合中寻找顶点j 使顶点j与S集合的边的权值最小
            int minW = Integer.MAX_VALUE;
            int j = 2;
            for (int x = 2; x <= n; x++) {
                if (d[x] == -1) {
                    // 顶点x已加入到集合S
                    continue;
                }

                if (d[x] < minW) {
                    minW = d[x];
                    j = x;
                }
            }

            // 将最短边e={i, j}加入树T
            int i = near[j];
            edges.add(new int[]{i, j, minW});
            // 将顶点j加入集合S
            S.add(j);
            // 将顶点j移除集合V-S
            d[j] = -1;

            // 更新near和d
            for (int x = 2; x <= n; x++) {
                if (d[x] == -1) {
                    // 顶点x已加入到集合S
                    continue;
                }

                int[] ews = w[j][x];
                if (null != ews) {
                    for (int ew : ews) {
                        if (ew < d[x]) {
                            d[x] = ew;
                            near[x] = j;
                        }
                    }
                }
            }
        }

        return edges;
    }

}
