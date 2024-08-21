package org.example.greedy_approach;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class HuffmanTest {

    int n1 = 8;
    float[] f1 = new float[]{-1, 0.05f, 0.05f, 0.10f, 0.15f, 0.25f, 0.10f, 0.10f, 0.20f};

    int n2 = 6;
    float[] f2 = new float[]{-1, 21, 10, 32, 41, 18, 70};

    @Test
    void binaryTree() {
        Huffman.Node root = Huffman.binaryTree(n1, f1);

        List<Huffman.Node> leaves = new ArrayList<>(n1);
        Huffman.encode(root, "", leaves);
        for (Huffman.Node node : leaves) {
            System.out.printf("字符id: %s, 频率: %s, 二元前缀码: %s\n", node.id, node.frequency, node.code);
        }

        float averageDepth = Huffman.calculateDepth(leaves);
        System.out.printf("Huffman树的平均深度(二元前缀码的平均码长): %s\n", averageDepth);
    }
}