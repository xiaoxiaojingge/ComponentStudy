package com.itjing.nowcoder;

import com.itjing.leetcode.ListNode;

/**
 * @author: lijing
 * @Date: 2021年09月17日 15:00
 * @Description:
 */
public class NC78反转链表 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);

        ListNode p = head;
        System.out.println("反转前：");
        while (p != null) {
            System.out.print(p.val + "\t");
            p = p.next;
        }

        System.out.println("\n反转后：");
//        p = ReverseList(head);
        p = ReverseList2(head);
        while (p != null) {
            System.out.print(p.val + "\t");
            p = p.next;
        }

    }

    /**
     * 使用递归
     *
     * @param head
     * @return
     */
    public static ListNode ReverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode last = ReverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    /**
     * 使用头插方式
     *
     * @param head
     * @return
     */
    public static ListNode ReverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head; // 当前结点
        ListNode next = null; // 存储后一节点，有用
        ListNode reverseHead = new ListNode(0); // 反转后临时头结点
        while (cur != null) {
            next = cur.next; // 存储后一节点，便于当前结点后移
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }
        return reverseHead.next;
    }
}
