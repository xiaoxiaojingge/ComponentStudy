package com.itjing.nowcoder;

import com.itjing.leetcode.ListNode;
import com.itjing.util.ListNodeUtil;

import java.util.ArrayList;

/**
 * @author: lijing
 * @Date: 2021年09月21日 8:25
 * @Description: 给定一个无序单链表，实现单链表的排序(按升序排序)。
 */
public class NC70单链表的排序 {

	public static void main(String[] args) {
		ListNode head = ListNodeUtil.generateListNode();
		ListNode p = sortInList(head);
		while (p != null) {
			System.out.print(p.val + "\t");
			p = p.next;
		}

	}

	/**
	 * 给定一个无序单链表，实现单链表的排序(按升序排序)。
	 * @param head
	 * @return
	 */
	public static ListNode sortInList(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ArrayList<Integer> list = new ArrayList<>();
		ListNode tmp = head;
		while (tmp != null) {
			list.add(tmp.val);
			tmp = tmp.next;
		}
		list.sort((a, b) -> {
			return a - b;
		});
		ListNode temp = head;
		int i = 0;
		while (temp != null) {
			temp.val = list.get(i++);
			temp = temp.next;
		}
		return head;
	}

	/**
	 * 归并排序 思路：先利用快慢指针找出链表的中点，然后分为两个链表，一直分，知道无法分为止，然后自底而上排序归并
	 * @param head
	 * @return
	 */
	public static ListNode sortInList2(ListNode head) {
		if (head == null || head.next == null)
			return head;
		// 使用快慢指针找到中点
		ListNode slow = head, fast = head.next;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		// 分割为两个链表
		ListNode newList = slow.next;
		slow.next = null;
		// 将两个链表继续分割
		ListNode left = sortInList(head);
		ListNode right = sortInList(newList);

		ListNode lhead = new ListNode(-1);
		ListNode res = lhead;
		// 归并排序
		while (left != null && right != null) {
			if (left.val < right.val) {
				lhead.next = left;
				left = left.next;
			}
			else {
				lhead.next = right;
				right = right.next;
			}
			lhead = lhead.next;
		}
		// 判断左右链表是否为空
		lhead.next = left != null ? left : right;
		return res.next;
	}

}
