package com.itjing.nowcoder;

import com.itjing.leetcode.ListNode;

/**
 * @author: lijing
 * @Date: 2021年09月21日 8:48
 * @Description: 输入两个无环的单向链表，找出它们的第一个公共结点，如果没有公共节点则返回空。
 */
public class NC66两个链表的第一个公共结点 {


    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode pA = pHead1, pB = pHead2;
        while (pA != pB) {
            pA = pA != null ? pA.next : pHead2;
            pB = pB != null ? pB.next : pHead1;
        }
        return pA;
    }
}
