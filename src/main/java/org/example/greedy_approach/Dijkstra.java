package org.example.greedy_approach;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Dijkstra {

    /**
     * 带权有向图G=<V, E, W>的单源最短路径
     *
     * @param n      顶点个数
     * @param w      [i][j] 标号i顶点到标号j顶点的边 的 权值 (-1表示 顶点i到顶点j没有边)
     * @param source 源点标号
     */
    public static int[] singleSourceShortestPath(int n, int[][] w, int source) {
        // S集合
        List<Integer> S = new ArrayList<>();
        // V-S集合
        List<Integer> VminusS = new ArrayList<>();

        // [j] 源点source到顶点j 相对于S集合的最短路径长度
        int[] dist = new int[n + 1];
        // [j] 源点source到顶点j 相对于S集合的最短路径上顶点j前一个顶点的标号
        int[] prev = new int[n + 1];


        // 初始化S集合
        S.add(source);
        // 初始化V-S集合
        for (int j = 1; j < source; j++) {
            VminusS.add(j);
        }
        for (int j = source + 1; j <= n; j++) {
            VminusS.add(j);
        }
        // 初始化dist和prev
        dist[source] = 0;
        for (Integer j : VminusS) {
            dist[j] = w[source][j];
            prev[j] = 1;
        }


        while (!VminusS.isEmpty()) {
            // 从V-S集合中寻找j  使源点source到顶点j 相对于S集合的最短路径长度 最小
            int min = -1;
            int j = 0;
            for (Integer k : VminusS) {
                if ((min == -1) || (min > dist[k])) {
                    min = dist[k];
                    j = k;
                }
            }

            // 更新S集合
            S.add(j);
            // 更新V-S集合
            int finalJ = j;
            VminusS.removeIf(k -> k.equals(finalJ));
            // 更新dist和prev
            for (Integer k : VminusS) {
                if (w[j][k] == -1) {
                    // 顶点j到顶点k没有边
                    continue;
                }

                if ((dist[k] == -1) || (dist[j] + w[j][k] < dist[k])) {
                    // 源点通过顶点j到顶点k的路径 比 原路径 更短
                    dist[k] = dist[j] + w[j][k];
                    prev[k] = j;
                }
            }
        }

        return prev;
    }

    // 打印源到目标的最短路径
    public static void printShortestPath(int source, int target, int[] prev) {
        if (source == target) {
            return;
        }

        System.out.printf("(%s, %s): ", source, target);

        StringJoiner stringJoiner = new StringJoiner("<-");
        stringJoiner.add("" + target);

        while (prev[target] != source) {
            stringJoiner.add("" + prev[target]);
            target = prev[target];
        }

        stringJoiner.add("" + source);

        System.out.printf("\t%s\n", stringJoiner);
    }

}
