package com.itjing.leetcode;

/**
 * @author: lijing
 * @Date: 2021年09月20日 11:51
 * @Description: 合并两个有序链表
 */
public class Q21_合并两个有序链表 {

    /**
     * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 创建一个临时头节点，连接合并的两个链表
        ListNode tempHead = new ListNode(0);
        // 当前结点初始指向临时头节点
        ListNode cur = tempHead;
        // 同时遍历两个链表
        while (l1 != null && l2 != null) {

            if (l1.val < l2.val) {
                // 如果l1的值小于l2，则将其加到cur后，l1节点后移
                cur.next = l1;
                l1 = l1.next;
            } else {
                // 如果l2的值小于l1，则将其加到cur后，l2节点后移
                cur.next = l2;
                l2 = l2.next;
            }
            // cur 指向新的下一节点
            cur = cur.next;
        }
        // 如果 l1 不为 null，则 l2 先遍历完成，将 l1 剩余加到 cur 后
        if (l1 != null) {
            cur.next = l1;
        }

        // 同上
        if (l2 != null) {
            cur.next = l2;
        }
        return tempHead.next;
    }
}
