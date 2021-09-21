package com.itjing.nowcoder;

import com.itjing.leetcode.ListNode;
import com.itjing.util.ListNodeUtil;

/**
 * @author: lijing
 * @Date: 2021年09月17日 16:07
 * @Description:
 */
public class NC69链表中倒数最后k个结点 {

    public static void main(String[] args) {
        ListNode head = ListNodeUtil.generateListNode();
        ListNode pHead = FindKthToTail(head, 2);
        while (pHead != null) {
            System.out.println(pHead.val + "\t");
            pHead = pHead.next;
        }

    }


    public static ListNode FindKthToTail(ListNode pHead, int k) {
        int sum = 0;
        ListNode p = pHead;
        while (p != null) {
            sum++;
            p = p.next;
        }
        if (sum < k || k <= 0) {
            return null;
        }
        for (int i = 0; i < sum - k; i++) {
            pHead = pHead.next;
        }
        return pHead;
    }
}
