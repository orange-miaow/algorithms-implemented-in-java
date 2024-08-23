package org.example.greedy_approach;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Kruskal {

    /**
     * 连通图G=<V, E, W>的最小生成树
     * @param n 顶点个数
     * @param w [i][j] 第i个顶点到第j个顶点的边 的 权值数组 (i到j的边可能有多条)
     */
    public static List<int[]> minimumSpanningTree(int n, int[][][] w) {
        // 构造Edges
        List<Edge> edges = constructEdges(n, w);
        // 按照权从小到大顺序排序G中的边 使得E={e1,e2,...,em}
        edges.sort((o1, o2) -> {
            if (o1.weight < o2.weight) {
                return -1;
            } else if (o1.weight > o2.weight) {
                return 1;
            }
            return 0;
        });

        // 最小生成树T的Edges
        List<int[]> TEdges = new ArrayList<>(n - 1);

        // 记录每个顶点所在的连通子集标识
        int[] FIND = new int[n + 1];
        // 记录连通子集
        Map<Integer, List<Integer>> connectedSubsets = new HashMap<>(); // k: 连通子集标识 v: 连通子集
        // 初始化FIND和connectedSubsets 每个顶点单独形成一个连通子集
        for (int i = 1; i <= n; i++) {
            FIND[i] = i;
            connectedSubsets.put(i, new ArrayList<>(List.of(i)));
        }

        while (TEdges.size() < n - 1) {
            // 取出权最小的边e={i, j}
            Edge e = edges.get(0);
            // 判断i和j是否在同一个连通分支 即加入e是否会构成回路
            if (FIND[e.i] != FIND[e.j]) {
                // 不在同一个连通子集
                // 将边e加入T
                TEdges.add(new int[]{e.i, e.j, e.weight});

                // 更新FIND和connectedSubsets
                // 将 i所在的连通子集 与 j所在的连通子集 合并(将元素少的合并至元素多的子集)
                List<Integer> subsetOfI = connectedSubsets.get(FIND[e.i]);
                List<Integer> subsetOfJ = connectedSubsets.get(FIND[e.j]);
                if (subsetOfI.size() >= subsetOfJ.size()) {
                    subsetOfI.addAll(subsetOfJ);
                    connectedSubsets.remove(FIND[e.j]);

                    // 更新FIND
                    for (Integer i : subsetOfJ) {
                        FIND[i] = FIND[e.i];
                    }
                } else {
                    subsetOfJ.addAll(subsetOfI);
                    connectedSubsets.remove(FIND[e.i]);

                    // 更新FIND
                    for (Integer i : subsetOfI) {
                        FIND[i] = FIND[e.j];
                    }
                }
            }
            // E <- E - {e}
            edges.remove(0);
        }

        return TEdges;
    }

    private static List<Edge> constructEdges(int n, int[][][] w) {
        List<Edge> edges = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) { // j从(i + 1)开始 而不是从(i)开始 是因为不考虑环(过单一顶点的边)
                int[] ews = w[i][j];
                if (null == ews) {
                    continue;
                }

                Edge edge = new Edge(i, j, Integer.MAX_VALUE);
                // 如果两个顶点之间有平行边 那么取其中最短的一条
                for (int ew : ews) {
                    if (ew < edge.weight) {
                        edge.weight = ew;
                    }
                }
                edges.add(edge);
            }
        }

        return edges;
    }

    public static class Edge {
        // 顶点i
        public int i;
        // 顶点j
        public int j;
        // 权值
        public int weight;

        public Edge(int i, int j, int weight) {
            this.i = i;
            this.j = j;
            this.weight = weight;
        }
    }

}
