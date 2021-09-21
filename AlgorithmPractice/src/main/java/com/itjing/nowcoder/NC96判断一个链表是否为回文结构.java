package com.itjing.nowcoder;

import com.itjing.leetcode.ListNode;
import com.itjing.util.ListNodeUtil;

/**
 * @author: lijing
 * @Date: 2021年09月18日 18:40
 * @Description: 判断一个链表是否为回文结构
 */
public class NC96判断一个链表是否为回文结构 {

    public static void main(String[] args) {
        ListNode head = ListNodeUtil.generateListNode();
        ListNode p = reverse(head);
        while (p != null) {
            System.out.print(p.val + "\t");
            p = p.next;
        }
    }

    /**
     * 判断一个链表是否为回文结构
     *
     * @param head
     * @return
     */
    public static boolean isPail(ListNode head) {
        // 一个节点则是回文结构
        if (head.next == null) {
            return true;
        }
        // 使用快慢指针，慢指针每次移动一步，快指针每次移动两步
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 这时 slow 即中间节点

        // 如果 fast != null ，证明链表节点个数为奇数,中间节点还需要向后移动一位
        if (fast != null) {
            slow = slow.next;
        }

        ListNode left = head;
        // right 为中点后面的链表反转后的头结点
        ListNode right = reverse(slow);

        // 比较节点数据
        while (right != null) {
            if (left.val != right.val)
                return false;
            left = left.next;
            right = right.next;
        }
        return true;
    }

    /**
     * 反转链表，別轻易使用递归，容易栈内存溢出
     *
     * @param head
     * @return
     */
    public static ListNode reverse(ListNode head) {
        ListNode pre = null, cur = head, next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}