package com.itjing.leetcode.反转链表;

import com.itjing.leetcode.ListNode;
import com.itjing.util.ListNodeUtil;

/**
 * @author: lijing
 * @Date: 2021年09月08日 10:40
 * @Description: 题目地址： https://leetcode-cn.com/problems/reverse-linked-list/
 * 参考： https://labuladong.gitee.io/algo/2/18/17/
 */
public class Ques206_反转链表 {

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
     * @param head
     * @return
     */
    ListNode reverseList2(ListNode head) {
        // 当前节点的前驱节点
        ListNode pre = null;
        // 当前节点
        ListNode cur = head;
        // 当前节点的后继节点
        ListNode next = null;
        while (cur != null) {
            // 首先保存当前节点的后继节点
            next = cur.next;
            // 将当前节点指向它的前驱节点
            cur.next = pre;
            // 节点后移
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode head = ListNodeUtil.generateListNode();

        ListNode revHead = reverseList(head);
        for (ListNode p = revHead; p != null; p = p.next) {
            System.out.print(p.val + " ");
        }
    }
}
