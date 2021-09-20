package com.itjing.leetcode.反转链表;

import com.itjing.leetcode.ListNode;

/**
 * @author: lijing
 * @Date: 2021年09月18日 9:46
 * @Description:
 */
public class Q234_回文链表 {
    /**
     * 判断是否为回文链表
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        // 使用快慢指针找到链表中点
        ListNode slow, fast;
        slow = fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // slow 指针现在指向链表中点

        // 如果fast指针没有指向null，说明链表长度为奇数，slow还要再前进一步
        if (fast != null)
            slow = slow.next;

        ListNode left = head;
        ListNode right = reverse(slow);

        // 比较是否为回文
        while (right != null) {
            if (left.val != right.val)
                return false;
            left = left.next;
            right = right.next;
        }
        return true;
    }


    /**
     * 反转链表
     *
     * @param head
     * @return
     */
    public ListNode reverse(ListNode head) {
        ListNode pre = null, // 前一个结点
                cur = head, // 当前结点
                next; // 保存下一个节点数据，便于当前节点移动

        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }


}

