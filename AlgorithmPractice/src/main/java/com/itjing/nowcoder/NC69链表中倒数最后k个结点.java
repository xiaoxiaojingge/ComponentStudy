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
        ListNode pHead = FindKthToTail2(head, 4);
        while (pHead != null) {
            System.out.print(pHead.val + "\t");
            pHead = pHead.next;
        }

    }


    /**
     * 输入一个长度为的链表，设链表中的元素的值为ai，
     * 输出一个链表，该输出链表包含原链表中从倒数第k个结点至尾节点的全部节点。
     * 如果该链表长度小于k，请返回一个长度为 0 的链表。
     *
     * @param pHead
     * @param k
     * @return
     */
    public static ListNode FindKthToTail(ListNode pHead, int k) {
        int sum = 0;
        ListNode p = pHead;
        // 先求节点总数
        while (p != null) {
            sum++;
            p = p.next;
        }
        if (sum < k || k <= 0) {
            return null;
        }
        // 让节点从头后移 sum-k 个，就是倒数第k个
        for (int i = 0; i < sum - k; i++) {
            pHead = pHead.next;
        }
        return pHead;
    }

    /**
     * 使用双指针
     * 我们的思路还是使用快慢指针，让快指针先走 k 步，
     * 然后快慢指针开始同速前进。这样当快指针走到链表末尾 null 时，
     * 慢指针所在的位置就是倒数第 k 个链表节点（k 不会超过链表长度）。
     *
     * @param pHead
     * @param k
     * @return
     */
    public static ListNode FindKthToTail2(ListNode pHead, int k) {
        // 空链表，直接返回空
        if(pHead == null){
            return null;
        }
        ListNode slow, fast;
        slow = fast = pHead;
        // 让快指针先走 k 步
        while (k > 0) {
            // 该链表长度小于k，则返回空
            if (fast == null) {
                return null;
            }
            fast = fast.next;
            k--;
        }
        if (fast == null) {
            // 如果此时快指针走到头了，
            // 说明倒数第 n 个节点就是第一个节点
            return pHead;
        }
        // 让慢指针和快指针同步向前
        while (fast != null && fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // 当快指针走到链表末尾 null 时，慢指针所在的位置就是倒数第 n 个链表节点
        return slow.next;
    }
}
