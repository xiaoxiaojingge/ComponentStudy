package com.itjing.leetcode;

/**
 * @author: lijing
 * @Date: 2021年09月18日 10:29
 * @Description:
 */
public class 剑指Offer06_从尾到头打印链表 {

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

    public int sumNode(ListNode p) {
        int sum = 0;
        while (p != null) {
            sum++;
            p = p.next;
        }
        return sum;
    }

    public int[] reversePrint(ListNode head) {
        ListNode p = reverse(head);
        int sum = sumNode(p);
        int[] arr = new int[sum];
        sum = 0;
        while (p != null) {
            arr[sum++] = p.val;
            p = p.next;
        }
        return arr;
    }
}
