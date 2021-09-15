package com.itjing.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lijing
 * @Date: 2021年09月09日 18:38
 * @Description:
 */
public class Q94_二叉树的中序遍历 {

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

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        inorder(root, res);
        return res;
    }

    /**
     * 定义 inorder(root) 表示当前遍历到 root 节点的答案，
     * 那么按照定义，我们只要递归调用 inorder(root.left) 来遍历 root 节点的左子树，
     * 然后将 root 节点的值加入答案，再递归调用inorder(root.right)
     * 来遍历 root 节点的右子树即可，递归终止的条件为碰到空节点。
     *
     * @param root
     * @param res
     */
    public void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }

        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }
}
