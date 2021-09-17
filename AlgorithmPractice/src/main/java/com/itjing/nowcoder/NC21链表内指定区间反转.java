package com.itjing.nowcoder;

/**
 * @author: lijing
 * @Date: 2021年09月17日 15:50
 * @Description:
 */
public class NC21链表内指定区间反转 {
    static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }


    static ListNode successor = null; // 后驱节点

    /**
     * 反转链表前n个节点
     *
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
     * 反战链表指定区间节点
     *
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

}

