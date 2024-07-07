package com.itjing.leetcode;

/**
 * @author: lijing
 * @Date: 2021年09月28日 8:43
 * @Description: 翻转一棵二叉树。 4 / \ 2 7 / \ / \ 1 3 6 9
 *
 * ↓
 *
 * 4 / \ 7 2 / \ / \ 9 6 3 1
 *
 */
public class Q226_翻转二叉树 {

	public TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return root;
		}
		// 交换左右节点
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;

		// 递归交换左右节点执行相同逻辑
		invertTree(root.left);
		invertTree(root.right);

		return root;
	}

}