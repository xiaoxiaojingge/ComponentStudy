package com.itjing.nowcoder;

import com.itjing.leetcode.ListNode;
import com.itjing.util.ListNodeUtil;

/**
 * @author: lijing
 * @Date: 2021年09月17日 15:50
 * @Description:
 */
public class NC21链表内指定区间反转 {

	static ListNode successor = null; // 后驱节点

	/**
	 * 反转链表前n个节点
	 * @param head
	 * @param n
	 * @return
	 */
	public static ListNode reverseN(ListNode head, int n) {
		if (n == 1) {
			successor = head.next;
			return head;
		}
		ListNode last = reverseN(head.next, n - 1);
		head.next.next = head;
		head.next = successor;
		return last;
	}

	/**
	 * 反转链表指定区间节点
	 * @param head
	 * @param m
	 * @param n
	 * @return
	 */
	public static ListNode reverseBetween(ListNode head, int m, int n) {
		if (m == 1) {
			// 如果m=1即，反转前n个节点
			return reverseN(head, n);
		}
		head.next = reverseBetween(head.next, m - 1, n - 1);
		return head;
	}

	/**
	 * 反转链表指定区间节点
	 * @param head
	 * @param m
	 * @param n
	 * @return
	 */
	public static ListNode reverseBetweenMY(ListNode head, int m, int n) {
		// 待反转区间头结点
		ListNode startNode = null;
		// 待反转区间尾结点
		ListNode endNode = null;
		// 待反转区间头结点的前驱节点
		ListNode preStartNode = null;

		ListNode p = head;
		for (int i = 0; i < m - 1; i++) {
			preStartNode = p;
			p = p.next;
		}
		startNode = p;

		// System.out.println("preStartNode = " + preStartNode.val);
		System.out.println("startNode = " + startNode.val);

		// 待反转区间尾结点的后继节点
		ListNode afterEndNode = null;
		for (int i = 0; i < n - m; i++) {
			p = p.next;
		}
		endNode = p;
		afterEndNode = endNode.next;

		System.out.println("endNode = " + endNode.val);
		// System.out.println("afterEndNode = " + afterEndNode.val);

		// 反转startNode到endNode之间的节点
		ListNode cur = startNode;
		ListNode pre = null;
		ListNode next;

		while (cur != afterEndNode) {
			next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		// 连接相关节点
		if (preStartNode != null) {
			preStartNode.next = endNode;
		}
		startNode.next = afterEndNode;

		/*
		 * System.out.println("pre = " + pre.val); System.out.println("cur = " + cur.val);
		 * System.out.println("pre.next = " + pre.next.val);
		 */
		System.out.println(afterEndNode);
		if (preStartNode == null) {
			return endNode;
		}
		return head;
	}

	public static void main(String[] args) {
		ListNode head = ListNodeUtil.generateListNode();
		ListNode p = reverseBetweenMY(head, 2, 5);
		while (p != null) {
			System.out.print(p.val + "\t");
			p = p.next;
		}

	}

}
