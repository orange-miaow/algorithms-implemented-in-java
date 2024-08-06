package org.example.dynamic_programming;

import java.util.StringJoiner;

public class MultiSourceMultiTargetShortestPath {

    /**
     *
     * @param pointLabels
     * 存放 点列的标签
     * @param unidirectionalEdges
     * [点列][点][0] 存放 本点至前一列可访问的点的序号
     * [点列][点][1] 存放 本点至前一列可访问的点的距离
     * @param optimalPoints
     * [点列][点][0] 存放 本点至前一列可访问的点中最佳点的序号
     * [点列][点][1] 存放 本点至终点的最短路径长度
     */
    public static void findPath(String[] pointLabels, int[][][][] unidirectionalEdges, int[][][] optimalPoints) {
        for (int i = 0; i < unidirectionalEdges.length; i++) { // 点列
            if (i == 0) {
                for (int j = 0; j < unidirectionalEdges[i].length; j++) { // 点
                    int[] indexs = unidirectionalEdges[i][j][0]; // 本点至前一列可访问的点的序号
                    int[] pathLengths = unidirectionalEdges[i][j][1]; // 本点至前一列可访问的点的距离

                    int shortestPathLength = pathLengths[0];
                    optimalPoints[i][j][0] = indexs[0]; // 本点至前一列可访问的点中最佳点的序号
                    optimalPoints[i][j][1] = shortestPathLength; // 本点至终点的最短路径长度

                    for (int k = 1; k < pathLengths.length; k++) {
                        if (pathLengths[k] < shortestPathLength) {
                            shortestPathLength = pathLengths[k];
                            optimalPoints[i][j][0] = indexs[k];
                            optimalPoints[i][j][1] = shortestPathLength;
                        }
                    }
                }

                continue;
            }

            for (int j = 0; j < unidirectionalEdges[i].length; j++) { // 点
                int[] indexs = unidirectionalEdges[i][j][0]; // 本点至前一列可访问的点的序号
                int[] pathLengths = unidirectionalEdges[i][j][1]; // 本点至前一列可访问的点的距离

                int shortestPathLength = pathLengths[0] + optimalPoints[i - 1][indexs[0]][1];
                optimalPoints[i][j][0] = indexs[0]; // 本点至前一列可访问的点中最佳点的序号
                optimalPoints[i][j][1] = shortestPathLength; // 本点至终点的最短路径长度

                for (int k = 1; k < pathLengths.length; k++) {
                    if (pathLengths[k] + optimalPoints[i - 1][indexs[k]][1] < shortestPathLength) {
                        shortestPathLength = pathLengths[k] + optimalPoints[i - 1][indexs[k]][1];
                        optimalPoints[i][j][0] = indexs[k];
                        optimalPoints[i][j][1] = shortestPathLength;
                    }
                }
            }
        }

        int shortestPathLength = optimalPoints[optimalPoints.length - 1][0][1];
        int sourcePointIndex = 0;
        for (int j = 1; j < optimalPoints[optimalPoints.length - 1].length; j++) {
            if (optimalPoints[optimalPoints.length - 1][j][1] < shortestPathLength) {
                shortestPathLength = optimalPoints[optimalPoints.length - 1][j][1];
                sourcePointIndex = j;
            }
        }
        System.out.println("shortestPathLength: " + shortestPathLength);

        StringJoiner shortestPathStringJoiner = new StringJoiner("->");
        shortestPathStringJoiner.add(pointLabels[pointLabels.length - 1] + (sourcePointIndex + 1));
        int nextPointIndex = sourcePointIndex;
        for (int i = optimalPoints.length - 1; i >= 0; i--) {
            nextPointIndex = optimalPoints[i][nextPointIndex][0];
            shortestPathStringJoiner.add(pointLabels[i] + (nextPointIndex + 1));
        }

        System.out.println("shortestPath: " + shortestPathStringJoiner);
    }

}
