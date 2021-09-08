package com.itjing.leetcode.反转链表;

/**
 * @author: lijing
 * @Date: 2021年09月08日 11:03
 * @Description: 题目地址： https://leetcode-cn.com/problems/reverse-linked-list-ii/
 */
public class Ques92_反转链表II {
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


    static ListNode successor = null; // 后驱节点

    /**
     * 反转链表前N个节点
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }

        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN(head.next, n - 1);

        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;
        return last;
    }

    /**
     * 反转某个区间的链表
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == 1) {
            // 这是就相当于反转链表的前N个节点
            return reverseN(head, right);
        }
        /**
         * 如果 m != 1 怎么办？如果我们把 head 的索引视为 1，那么我们是想从第 m 个元素开始反转对吧；
         * 如果把 head.next 的索引视为 1 呢？
         * 那么相对于 head.next，反转的区间应该是从第 m - 1 个元素开始的；
         */
        // 前进到反转的起点触发 (反转链表的前N个节点)
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode revHead = reverseBetween(head, 2, 4);

//        ListNode revHead = reverseN(head, 2);

        for (ListNode p = revHead; p != null; p = p.next) {
            System.out.print(p.val + " ");
        }
    }
}
