package com.attilapalfi.hackerrank.datastructures.trees;

import java.util.*;

public class IsThisBst {

    public static boolean checkBST(Node root) {
        List<Integer> data = new ArrayList<>();

        inOrderTraversal(root, data);

        int iterator = 1;
        boolean isBst = true;
        int current = data.get(0);
        while (isBst && iterator < data.size()) {
            int next = data.get(iterator);
            if (next <= current) {
                isBst = false;
            }
            current = next;
            iterator++;
        }
        return isBst;
    }

    private static void inOrderTraversal(Node node, List<Integer> data) {
        if (node != null) {
            inOrderTraversal(node.left, data);

            // this is the visit
            data.add(node.data);

            inOrderTraversal(node.right, data);
        }
    }

    class Node {
        int data;
        Node left;
        Node right;
    }
}
