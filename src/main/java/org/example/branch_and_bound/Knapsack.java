package org.example.branch_and_bound;

import java.util.Arrays;

public class Knapsack {

    // 最大重量限制
    private int B;
    // [i] 物品i的价值
    private int[] v;
    // [i] 物品i的重量
    private int[] w;

    // 物品数组 用于对物品按照单位重量价值从大到小顺序排序
    private Thing[] things;
    // 界函数值
    private int upperBoundFunctionValue;
    // 解向量
    private int[] solutionVector;

    public Knapsack(int B, int[] v, int[] w) {
        this.B = B;
        this.v = v;
        this.w = w;

        // 对物品按照单位重量价值从大到小顺序排序 即 v[i]/w[i] >= v[i + 1]/w[i + 1]
        this.things = new Thing[v.length];
        for (int i = 0; i < v.length; i++) {
            this.things[i] = new Thing(i, v[i], w[i]);
        }
        Arrays.sort(this.things, (o1, o2) -> {
            if (o1.unitValue > o2.unitValue) {
                return -1;
            } else if (o1.unitValue < o2.unitValue) {
                return 1;
            }
            return 0;
        });

        this.upperBoundFunctionValue = 0;
    }

    public Thing[] getThings() {
        return things;
    }

    public int getUpperBoundFunctionValue() {
        return upperBoundFunctionValue;
    }

    public int[] getSolutionVector() {
        return solutionVector;
    }

    /**
     * 在搜索树中搜寻最优解
     *
     * @param level  层级 level=0,1,2,...,n-1
     * @param vector 根结点至当前结点的向量
     */
    public void searchOptimalSolution(int level, int[] vector) {
        // 第level个物品之前的已装入背包的总价值
        int originalValue = 0;
        // 第level个物品之前的已装入背包的总重量
        int originalWeight = 0;
        for (int i = 0; i < level; i++) {
            if (vector[i] == 1) {
                originalValue += things[i].value;
                originalWeight += things[i].weight;
            }
        }

        // 第level个物品在最大重量限制下最多可以装入的个数
        int thingCount = (int) Math.floor((double) B / things[level].weight);

        for (int i = thingCount; i >= 0; i--) {
            // (判断 根结点至该结点的向量 是否 为部分可行解向量) 且 (判断 该结点的代价函数值 是否 大于界函数值)
            vector[level] = i;
            int currentWeight = originalWeight + i * things[level].weight;
            int currentValue = originalValue + i * things[level].value;

            if (currentWeight > B) {
                continue;
            }

            if (level == things.length - 1) {
                // 此时恰好遍历到最后一个物品 即最后一层
                if (currentValue > upperBoundFunctionValue) {
                    upperBoundFunctionValue = currentValue;
                    solutionVector = vector.clone();
                }

                continue;
            }

            // 剩余重量
            int remainder = B - currentWeight;
            // 计算代价函数值
            double costFunctionValue = currentValue;
            for (int k = level + 1; k < things.length; k++) {
                if (remainder >= things[k].weight) {
                    costFunctionValue += remainder * things[level + 1].unitValue;
                    break;
                }
            }

            if (costFunctionValue > upperBoundFunctionValue) {
                // 根据深度优先策略(DFS)遍历该结点的下一个结点
                searchOptimalSolution(level + 1, vector);
            } else {
                // 由该结点回溯至其父结点 之后根据深度优先策略(DFS)遍历父结点的下一个可用结点
            }
        }
    }

    public static class Thing {
        // 物品id
        public int id;
        // 物品价值
        public int value;
        // 物品重量
        public int weight;
        // 物品单位重量价值
        public double unitValue;

        public Thing(int id, int value, int weight) {
            this.id = id;
            this.value = value;
            this.weight = weight;
            this.unitValue = (double) value / weight;
        }
    }

}
