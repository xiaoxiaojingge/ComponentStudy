package com.itjing.leetcode;

/**
 * @author: lijing
 * @Date: 2021年09月09日 10:54
 * @Description:
 */
public class Q2_两数相加 {

	static class ListNode {

		int val;

		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}

	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode head = null, tail = null;
		// 进位值
		int carry = 0;
		while (l1 != null || l2 != null) {
			int n1 = l1 != null ? l1.val : 0;
			int n2 = l2 != null ? l2.val : 0;
			int sum = n1 + n2 + carry;
			if (head == null) {
				head = tail = new ListNode(sum % 10);
			}
			else {
				tail.next = new ListNode(sum % 10);
				tail = tail.next;
			}

			carry = sum / 10;

			if (l1 != null) {
				l1 = l1.next;
			}
			if (l2 != null) {
				l2 = l2.next;
			}
		}

		// 如果上述循环结束，进位值大于0，则最高位需要进位
		if (carry > 0) {
			tail.next = new ListNode(carry);
		}
		return head;
	}

	public static void main(String[] args) {
		ListNode l1 = new ListNode(2);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(3);

		ListNode l2 = new ListNode(5);
		l2.next = new ListNode(6);
		l2.next.next = new ListNode(4);

		ListNode result = addTwoNumbers(l1, l2);
		for (ListNode p = result; p != null; p = p.next) {
			System.out.print(p.val + " ");
		}
	}

}
