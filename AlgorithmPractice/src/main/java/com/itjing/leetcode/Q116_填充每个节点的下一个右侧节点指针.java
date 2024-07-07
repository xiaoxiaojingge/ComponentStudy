package com.itjing.leetcode;

/**
 * @author: lijing
 * @Date: 2021年09月28日 8:55
 * @Description: 填充每个节点的下一个右侧节点指针
 * <p>
 * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * <p>
 * struct Node { int val; Node *left; Node *right; Node *next; } 填充它的每个 next
 * 指针，让这个指针指向其下一个右侧节点。 如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * <p>
 * 初始状态下，所有 next 指针都被设置为 NULL。
 */
public class Q116_填充每个节点的下一个右侧节点指针 {

	public static void main(String[] args) {

	}

	/**
	 * 题解：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/solution/tian-chong-mei-ge-jie-dian-de-xia-yi-ge-you-ce-2-4/
	 * @param root
	 * @return
	 */
	public Node connect2(Node root) {
		if (root == null) {
			return root;
		}

		// 从根节点开始
		Node leftmost = root;

		while (leftmost.left != null) {

			// 遍历这一层节点组织成的链表，为下一层的节点更新 next 指针
			Node head = leftmost;

			while (head != null) {

				// CONNECTION 1
				head.left.next = head.right;

				// CONNECTION 2
				if (head.next != null) {
					head.right.next = head.next.left;
				}

				// 指针向后移动
				head = head.next;
			}

			// 去下一层的最左的节点
			leftmost = leftmost.left;
		}

		return root;
	}

	public Node connect(Node root) {
		if (root == null) {
			return root;
		}
		connectTwoNode(root.left, root.right);
		return root;
	}

	public void connectTwoNode(Node node1, Node node2) {
		if (node1 == null || node2 == null) {
			return;
		}

		node1.next = node2;
		// node1的左节点指向右节点
		connectTwoNode(node1.left, node1.right);
		// node2的左节点指向右节点
		connectTwoNode(node2.left, node2.right);
		// 将不相关的节点相连，即不是左右节点的两个节点相连
		connectTwoNode(node1.right, node2.left);
	}

}

class Node {

	public int val;

	public Node left;

	public Node right;

	public Node next;

	public Node() {
	}

	public Node(int _val) {
		val = _val;
	}

	public Node(int _val, Node _left, Node _right, Node _next) {
		val = _val;
		left = _left;
		right = _right;
		next = _next;
	}

}