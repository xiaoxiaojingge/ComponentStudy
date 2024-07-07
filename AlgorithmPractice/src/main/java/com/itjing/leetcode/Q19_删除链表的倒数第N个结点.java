package com.itjing.leetcode;

import com.itjing.util.ListNodeUtil;

/**
 * @author: lijing
 * @Date: 2021年10月05日 8:46
 * @Description: 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * <p>
 * 提示 链表中结点的数目为 sz 1 <= sz <= 30 0 <= Node.val <= 100 1 <= n <= sz
 */
public class Q19_删除链表的倒数第N个结点 {

	public static void main(String[] args) {
		ListNode head = ListNodeUtil.generateListNode();
		ListNode p = removeNthFromEnd(head, 1);
		while (p != null) {
			System.out.print(p.val + "\t");
			p = p.next;
		}
	}

	/**
	 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
	 * @param head
	 * @param n
	 * @return
	 */
	public static ListNode removeNthFromEnd(ListNode head, int n) {
		if (head == null) {
			return head;
		}
		// 定义双指针
		ListNode slow, fast;
		slow = fast = head;

		// 记录慢指针前驱节点，初始为虚拟节点
		ListNode pre = new ListNode(-1);

		// 快指针先走n步
		for (int i = 0; i < n; i++) {
			fast = fast.next;
		}
		// fast 为 null，说明待删除节点为头结点
		if (fast == null) {
			return head.next;
		}
		// 双指针同时走
		while (fast != null) {
			pre = slow;
			slow = slow.next;
			fast = fast.next;
		}
		// 快指针走到头了，则慢指针位置即为倒数第n个节点
		// 连接待删除的节点前驱和后继节点
		pre.next = slow.next;
		return head;
	}

}
