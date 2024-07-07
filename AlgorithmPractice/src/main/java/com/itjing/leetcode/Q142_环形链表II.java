package com.itjing.leetcode;

/**
 * @author: lijing
 * @Date: 2021年10月04日 15:29
 * @Description: 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 */
public class Q142_环形链表II {

	/**
	 * 通过快慢指针解决，有环的话，快慢指针节点一定会重合
	 * @param head
	 * @return
	 */
	public boolean hasCycle(ListNode head) {
		ListNode slow = head, fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				return true;
			}
		}
		return false;
	}

	public ListNode detectCycle(ListNode head) {
		// 定义快慢指针
		ListNode slow = head, fast = head;
		// 标识是否有环
		boolean hasCycle = false;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			// 重合，即相遇点
			if (slow == fast) {
				hasCycle = true;
				break;
			}
		}
		// 其中一种为null，则说明没有环
		/*
		 * if(fast==null||fast.next==null) return null;
		 */
		if (!hasCycle) {
			return null;
		}
		// 让慢指针回到链表头，这时候快指针还在相遇点
		slow = head;
		// 两个指针分别从链表头和相遇点出发，最后一定相遇于环入口。
		while (slow != fast) {
			fast = fast.next;
			slow = slow.next;
		}
		return slow;
	}

}
