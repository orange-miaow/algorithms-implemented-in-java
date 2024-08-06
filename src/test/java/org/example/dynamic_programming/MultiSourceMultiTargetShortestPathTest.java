package org.example.dynamic_programming;

import org.junit.jupiter.api.Test;

class MultiSourceMultiTargetShortestPathTest {


    // 存放 点列的标签
    private String[] pointLabels = new String[]{"T", "C", "B", "A", "S"};

    // [点列][点][0] 存放 本点至前一列可访问的点的序号
    // [点列][点][1] 存放 本点至前一列可访问的点的距离
    private int[][][][] unidirectionalEdges = new int[][][][]{
            {
                    {{0, 1}, {2, 5}},
                    {{1, 2}, {4, 3}},
                    {{2, 3}, {7, 7}},
                    {{3, 4}, {1, 6}}
            },
            {
                    {{0}, {9}},
                    {{0, 1}, {3, 6}},
                    {{1, 2}, {4, 2}},
                    {{2, 3}, {1, 4}},
                    {{3}, {3}}
            },
            {
                    {{0, 1}, {3, 4}},
                    {{1, 2}, {3, 2}},
                    {{2, 3}, {3, 1}},
                    {{3, 4}, {2, 5}}
            },
            {
                    {{0}, {6}},
                    {{0, 1}, {4, 7}},
                    {{1, 2}, {5, 4}},
                    {{2, 3}, {9, 4}},
                    {{3}, {3}}
            }
    };

    // [点列][点][0] 存放 本点至前一列可访问的点中最佳点的序号
    // [点列][点][1] 存放 本点至终点的最短路径长度
    private int[][][] optimalPoints = new int[][][]{
            {
                    {-1, -1},
                    {-1, -1},
                    {-1, -1},
                    {-1, -1}
            },
            {
                    {-1, -1},
                    {-1, -1},
                    {-1, -1},
                    {-1, -1},
                    {-1, -1}
            },
            {
                    {-1, -1},
                    {-1, -1},
                    {-1, -1},
                    {-1, -1}
            },
            {
                    {-1, -1},
                    {-1, -1},
                    {-1, -1},
                    {-1, -1},
                    {-1, -1}
            }
    };

    @Test
    void findPath() {
        MultiSourceMultiTargetShortestPath.findPath(pointLabels, unidirectionalEdges, optimalPoints);
    }
}