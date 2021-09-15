package com.itjing.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lijing
 * @Date: 2021年09月09日 18:56
 * @Description:
 */
public class Q590_N叉树的后序遍历 {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<Integer>();
        inorder(root, res);
        return res;
    }

    public void inorder(Node root, List<Integer> res) {
        if (root == null) {
            return;
        }

        for (int i = 0; i < root.children.size(); i++) {
            inorder(root.children.get(i), res);
        }

        res.add(root.val);
    }
}
