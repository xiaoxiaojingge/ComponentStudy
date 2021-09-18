package com.itjing.leetcode;

/**
 * @author: lijing
 * @Date: 2021年09月18日 10:44
 * @Description: 给定一个链表，判断链表中是否有环。
 */
public class Q141_环形链表 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 通过快慢指针解决，有环的话，快慢指针节点一定会重合
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head,
                fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

}
