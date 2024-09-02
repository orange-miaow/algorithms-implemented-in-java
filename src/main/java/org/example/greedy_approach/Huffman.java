package org.example.greedy_approach;

import java.util.ArrayList;
import java.util.List;

public class Huffman {

    /**
     * 构造一棵Huffman树
     *
     * @param n 字符集C={x1,x2,...,xn}的字符个数
     * @param f [i] 字符i的频率
     */
    public static Node binaryTree(int n, float[] f) {
        // 按频率递增构成队列Q
        List<Node> Q = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            Node node = new Node(i, f[i]);
            Q.add(node);
        }
        Q.sort((o1, o2) -> {
            if (o1.frequency < o2.frequency) {
                return -1;
            } else if (o1.frequency > o2.frequency) {
                return 1;
            }
            return 0;
        });

        int zId = n + 1;
        for (int i = 1; i <= n - 1; i++) {
            Node x = Q.get(0); // 从队列中取出频率最小的结点x
            Node y = Q.get(1); // 从队列中取出频率最小的结点y
            // 将x和y移除队列
            Q.remove(0);
            Q.remove(0);

            Node z = new Node(zId++, x.frequency + y.frequency); // 生成结点z f(z) = f(x) + f(y)
            // 将x, y分别作为z的左右孩子
            z.left = x;
            z.right = y;

            // 将结点z插入队列Q
            int j;
            for (j = 0; j < Q.size(); j++) {
                if (Q.get(j).frequency > z.frequency) {
                    break;
                }
            }
            Q.add(j, z);
        }

        Node root = Q.get(0); // 根结点
        return root;
    }

    // Huffman二元前缀码编码 同时提取出叶子结点
    // 使用 先根遍历
    public static void encode(Node node, String bits, List<Node> leaves) {
        if (null == node) {
            return;
        }

        node.code = bits;

        encode(node.left, node.code + "0", leaves);
        encode(node.right, node.code + "1", leaves);

        // 提取叶子结点
        if ((null == node.left) && (null == node.right)) {
            leaves.add(node);
        }
    }

    // 计算Huffman树的平均深度(二元前缀码的平均码长)
    public static float calculateDepth(List<Node> leaves) {
        float averageDepth = 0;
        for (Node node : leaves) {
            averageDepth += node.code.length() * node.frequency;
        }

        return averageDepth;
    }

    public static class Node {
        // 字符id
        public int id;
        // 字符频率
        public float frequency;
        // 二元前缀码
        public String code = "";

        // 左孩子
        public Node left;
        // 右孩子
        public Node right;

        public Node(int id, float frequency) {
            this.id = id;
            this.frequency = frequency;
        }
    }

}
