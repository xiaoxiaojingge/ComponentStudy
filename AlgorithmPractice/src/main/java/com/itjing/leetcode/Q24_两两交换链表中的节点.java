package com.itjing.leetcode;

import com.itjing.util.ListNodeUtil;

/**
 * @author: lijing
 * @Date: 2021年10月05日 9:06
 * @Description: 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 */
public class Q24_两两交换链表中的节点 {

    public static void main(String[] args) {
        ListNode head = ListNodeUtil.generateListNode();
        ListNode p = swapPairs(head);
        while (p != null) {
            System.out.print(p.val + "\t");
            p = p.next;
        }
    }

    public static ListNode swapPairs(ListNode head) {
        // 边界
        if (head == null || head.next == null) {
            return head;
        }

        // 当前节点
        ListNode cur = head;
        // 记录每两个节点的后一个节点
        ListNode nextNext;
        // 临时节点，用于交换
        ListNode tmp;
        // 记录交换后每第二组的前驱节点
        ListNode curNext;

        // 先交换
        tmp = cur;
        nextNext = cur.next.next;
        cur = cur.next;
        cur.next = tmp;
        tmp.next = nextNext;

        // 这时第二组的前驱节点是tmp
        curNext = tmp;

        // 记录头节点
        head = cur;

        // 到下一组
        for (int i = 0; i < 2; i++) {
            cur = cur.next;
        }


        while (cur != null && cur.next != null) {
            tmp = cur;
            nextNext = cur.next.next;
            cur = cur.next;
            cur.next = tmp;
            tmp.next = nextNext;

            // 连接两组节点
            curNext.next = cur;
            // 记录新的前驱节点
            curNext = tmp;

            for (int i = 0; i < 2; i++) {
                cur = cur.next;
            }

        }
        return head;
    }
}
