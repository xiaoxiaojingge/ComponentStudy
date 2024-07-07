package com.itjing.nowcoder;

import com.itjing.leetcode.ListNode;
import com.itjing.util.ListNodeUtil;

/**
 * @author: lijing
 * @Date: 2021年09月18日 18:02
 * @Description: 删除链表的倒数第n个节点
 */
public class NC53删除链表的倒数第n个节点 {

	public static void main(String[] args) {

		ListNode head = ListNodeUtil.generateListNode();

		ListNode p = removeNthFromEnd(head, 2);
		while (p != null) {
			System.out.print(p.val + "\t");
			p = p.next;
		}

	}

	/**
	 * 删除链表的倒数第n个节点
	 * @param head
	 * @param n
	 * @return
	 */
	public static ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode p = head;
		int sum = 0;
		while (p != null) {
			sum++;
			p = p.next;
		}
		// 如果倒数第n个节点是头结点
		if (sum == n) {
			return head.next;
		}
		ListNode pre = head, cur = head;
		for (int i = 0; i < sum - n; i++) {
			pre = cur;
			cur = cur.next;
		}
		// 断开当前结点，将此节点之前结点与后结点相连
		pre.next = cur.next;
		return head;
	}

	/**
	 * 使用双指针 给定一个链表，删除链表的倒数第 n 个节点并返回链表的头指针 题目保证 n 一定是有效的
	 * @param head
	 * @param n
	 * @return
	 */
	public static ListNode removeNthFromEnd2(ListNode head, int n) {
		// 空链表，直接返回空
		if (head == null) {
			return null;
		}
		ListNode slow, fast;
		slow = fast = head;
		// 让快指针先走 k 步
		while (n > 0) {
			fast = fast.next;
			n--;
		}
		if (fast == null) {
			// 如果此时快指针走到头了，
			// 说明倒数第 n 个节点就是第一个节点
			// 这里返回的head，根据实际情况写，有可能有的题目头结点没有值,是个虚节点
			return head.next;
		}
		// 让慢指针和快指针同步向前
		while (fast != null && fast.next != null) {
			fast = fast.next;
			slow = slow.next;
		}
		// 当快指针走到链表末尾 null 时，慢指针所在的位置就是倒数第 n 个链表节点
		// 直接指向往后第二个节点，即删除这个节点
		slow.next = slow.next.next;
		return head;
	}

}
