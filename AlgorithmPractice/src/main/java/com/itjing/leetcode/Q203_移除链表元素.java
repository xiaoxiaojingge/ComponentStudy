package com.itjing.leetcode;

import com.itjing.util.ListNodeUtil;

/**
 * @author: lijing
 * @Date: 2021年09月20日 19:12
 * @Description: 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 */
public class Q203_移除链表元素 {

	/**
	 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
	 * 题目不保证链表中节点的值互不相同
	 * @param head
	 * @param val
	 * @return
	 */
	public static ListNode removeElements(ListNode head, int val) {
		if (head == null) {
			return null;
		}

		while (head != null && head.val == val) {
			head = head.next;
		}

		if (head == null) {
			return null;
		}

		ListNode pre = null;
		ListNode cur = head;
		ListNode next = head.next;

		// 删除节点
		while (cur != null) {
			if (cur.val == val) {
				pre.next = cur.next;
			}
			// 两个不相同节点放在一起，才移动pre
			if (cur.next != null && cur.val != cur.next.val) {
				pre = cur;
			}
			cur = cur.next;
		}
		return head;
	}

	public static void main(String[] args) {
		ListNode head = ListNodeUtil.generateListNode();

		ListNode p = removeElements(head, 6);
		while (p != null) {
			System.out.print(p.val + "\t");
			p = p.next;
		}
	}

}
