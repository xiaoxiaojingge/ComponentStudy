package com.itjing.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lijing
 * @Date: 2021年09月09日 18:49
 * @Description:
 */
public class Q144_二叉树的前序遍历 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public  List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        inorder(root, res);
        return res;
    }

   
    public  void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }

        res.add(root.val);
        inorder(root.left, res);
        inorder(root.right, res);
    }
}
