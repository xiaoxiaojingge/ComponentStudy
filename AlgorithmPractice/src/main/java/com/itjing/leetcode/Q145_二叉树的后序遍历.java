package com.itjing.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lijing
 * @Date: 2021年09月09日 18:49
 * @Description:
 */
public class Q145_二叉树的后序遍历 {

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

	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		inorder(root, res);
		return res;
	}

	public void inorder(TreeNode root, List<Integer> res) {
		if (root == null) {
			return;
		}

		inorder(root.left, res);
		inorder(root.right, res);
		res.add(root.val); // 后序遍历根结点在最后取值
	}

}
