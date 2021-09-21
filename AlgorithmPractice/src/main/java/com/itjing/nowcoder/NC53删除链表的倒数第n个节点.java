package com.itjing.nowcoder;

import com.itjing.leetcode.ListNode;
import com.itjing.util.ListNodeUtil;

/**
 * @author: lijing
 * @Date: 2021年09月18日 18:02
 * @Description: 删除链表的倒数第n个节点
 */
public class NC53删除链表的倒数第n个节点 {


    public static void main(String[] args) {

        ListNode head = ListNodeUtil.generateListNode();

        ListNode p = removeNthFromEnd(head, 2);
        while (p != null) {
            System.out.print(p.val + "\t");
            p = p.next;
        }

    }

    /**
     * 删除链表的倒数第n个节点
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p = head;
        int sum = 0;
        while (p != null) {
            sum++;
            p = p.next;
        }
        // 如果倒数第n个节点是头结点
        if (sum == n) {
            return head.next;
        }
        ListNode pre = head, cur = head;
        for (int i = 0; i < sum - n; i++) {
            pre = cur;
            cur = cur.next;
        }
        // 断开当前结点，将此节点之前结点与后结点相连
        pre.next = cur.next;
        return head;
    }
}
