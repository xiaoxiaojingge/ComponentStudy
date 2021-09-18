package com.itjing.leetcode;

/**
 * @author: lijing
 * @Date: 2021年09月18日 11:15
 * @Description: 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
 */
public class Q876_链表的中间结点 {


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

    /**
     * 使用快慢指针求中间节点
     *
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
