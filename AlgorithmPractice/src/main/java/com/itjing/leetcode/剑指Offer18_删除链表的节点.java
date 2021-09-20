package com.itjing.leetcode;

/**
 * @author: lijing
 * @Date: 2021年09月18日 10:13
 * @Description: 删除链表的节点，题目保证链表中节点的值互不相同
 */
public class 剑指Offer18_删除链表的节点 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode p = deleteNode(head, 3);
        while (p != null) {
            System.out.print(p.val + "\t");
            p = p.next;
        }
    }

    /**
     * 删除链表的节点
     *
     * @param head
     * @param val
     * @return
     */
    public static ListNode deleteNode(ListNode head, int val) {
        if (head.val == val) {
            return head.next;
        }
        ListNode pre = head,
                cur = head.next;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            }
            pre = cur;
            cur = cur.next;
        }
        return head;
    }
}
