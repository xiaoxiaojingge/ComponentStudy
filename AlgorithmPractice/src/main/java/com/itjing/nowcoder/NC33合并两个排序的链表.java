package com.itjing.nowcoder;

import com.itjing.leetcode.ListNode;
import com.itjing.util.ListNodeUtil;

/**
 * @author: lijing
 * @Date: 2021年09月21日 7:34
 * @Description: 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 */
public class NC33合并两个排序的链表 {


    /**
     * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
     *
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode Merge(ListNode list1, ListNode list2) {
        // 定义一个临时的头结点
        ListNode tempHead = new ListNode(0);
        ListNode cur = tempHead;
        while (list1 != null && list2 != null) {
            // list1.val < list2.val ---> 拿出来，指针后移
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                // 同上
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        // 拼接剩下的
        if (list1 != null) {
            cur.next = list1;
        }
        if (list2 != null) {
            cur.next = list2;
        }
        return tempHead.next;
    }

    public static void main(String[] args) {
        ListNode l1 = ListNodeUtil.generateListNode();

        ListNode l2 = ListNodeUtil.generateListNode();

        ListNode p = Merge(l1, l2);
        while (p != null) {
            System.out.print(p.val + "\t");
            p = p.next;
        }
    }
}
