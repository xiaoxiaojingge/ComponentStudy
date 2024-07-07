package com.itjing.leetcode;

/**
 * @author: lijing
 * @Date: 2021年09月28日 8:45
 * @Description: 树结构
 */
public class TreeNode {

	public int val;

	public TreeNode left;

	public TreeNode right;

	TreeNode() {
	}

	public TreeNode(int val) {
		this.val = val;
	}

	public TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}

}