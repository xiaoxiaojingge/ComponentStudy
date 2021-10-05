package com.itjing.leetcode;

import com.itjing.util.ListNodeUtil;

/**
 * @author: lijing
 * @Date: 2021年10月05日 10:29
 * @Description: 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 */
public class Q61_旋转链表 {
    public static void main(String[] args) {
        ListNode head = ListNodeUtil.generateListNode();
        ListNode p = rotateRight(head, 1);
        ListNodeUtil.print(p);
    }

    /**
     * 记得画图验证结果
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        ListNode p = head;
        // 记录节点数量
        int count = 0;
        while (p != null) {
            count++;
            p = p.next;
        }
        // 计算需要找到倒数第几个节点
        k = k % count;
        if (count == 1 || k == 0) {
            return head;
        }

        // 定义双指针，先找到倒数第k个节点
        ListNode slow, fast;
        slow = fast = head;

        // 记录慢指针前驱节点，初始为虚拟节点
        ListNode pre = new ListNode(-1);

        // 记录快指针前驱节点
        ListNode fastPre = null;

        // 快指针先走n步
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        // 双指针同时走
        while (fast != null) {
            pre = slow;
            slow = slow.next;
            // 记录快指针前驱节点
            fastPre = fast;
            fast = fast.next;
        }

        // 将链表首尾闭成一个环
        fastPre.next = head;
        // 断开链表
        pre.next = null;
        return slow;
    }
}
