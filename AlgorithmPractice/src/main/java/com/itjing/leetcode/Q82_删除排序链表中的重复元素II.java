package com.itjing.leetcode;

import com.itjing.util.ListNodeUtil;

/**
 * @author: lijing
 * @Date: 2021年10月04日 14:12
 * @Description:
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，
 * 请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
 */
public class Q82_删除排序链表中的重复元素II {

    public static void main(String[] args) {
        ListNode head = ListNodeUtil.generateListNode();
        ListNode p = deleteDuplicates(head);
        while (p != null) {
            System.out.print(p.val + "\t");
            p = p.next;
        }
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        // 待删除链表当前节点
        ListNode cur = head;
        // 结果链表虚拟头节点
        ListNode newHead = new ListNode(-1);
        // 结果链表当前节点
        ListNode node = newHead;
        while (cur != null && cur.next != null) {
            // 如果当前节点值和下一个节点值不同，则添加到结果链表后
            if (cur.val != cur.next.val) {
                node.next = new ListNode(cur.val);
                node = node.next;
            } else {
                // 遇到相同的数，节点后移，一直到不同的数为止
                while (cur != null && cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
            }
            cur = cur.next;
        }
        node.next = cur;
        return newHead.next;
    }
}
