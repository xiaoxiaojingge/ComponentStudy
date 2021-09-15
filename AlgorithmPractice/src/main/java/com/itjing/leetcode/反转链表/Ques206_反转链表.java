package com.itjing.leetcode.反转链表;

/**
 * @author: lijing
 * @Date: 2021年09月08日 10:40
 * @Description: 题目地址： https://leetcode-cn.com/problems/reverse-linked-list/
 * 参考： https://labuladong.gitee.io/algo/2/18/17/
 */
public class Ques206_反转链表 {

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
     * 递归反转整个链表
     *
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    /**
     * 反转以 a 为头结点的链表,循环实现
     *
     * @param a
     * @return
     */
    ListNode reverse(ListNode a) {
        ListNode pre, cur, nxt;
        pre = null;
        cur = a;
        nxt = a;
        while (cur != null) {
            nxt = cur.next;
            // 逐个结点反转
            cur.next = pre;
            // 更新指针位置
            pre = cur;
            cur = nxt;
        }
        // 返回反转后的头结点
        return pre;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

//        ListNode head = null;

        ListNode revHead = reverseList(head);
        for (ListNode p = revHead; p != null; p = p.next) {
            System.out.print(p.val + " ");
        }
    }
}
