package com.itjing.nowcoder;

/**
 * @author: lijing
 * @Date: 2021年09月18日 18:16
 * @Description: 给出一个升序排序的链表，删除链表中的所有重复出现的元素，只保留原链表中只出现一次的元素。
 */
public class NC24删除有序链表中重复的元素II {

    static class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(3);


        ListNode p = deleteDuplicates(head);
        while (p != null) {
            System.out.print(p.val + "\t");
            p = p.next;
        }

    }

    /**
     * 给出一个升序排序的链表，删除链表中的所有重复出现的元素，
     * 只保留原链表中只出现一次的元素。
     *
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates(ListNode head) {
        // 双指针解决
        ListNode pre = new ListNode(-1);
        ListNode newHead = pre;
        pre.next = head;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            // 当遇到当前节点值和下一节点值相等的节点时
            // 进行while循环找到下一个不相等的节点，挂到pre节点上
            // 然后，当前结点指向它
            if (cur.val == cur.next.val) {
                ListNode temp = cur.next;
                while (temp != null && temp.val == cur.val) {
                    temp = temp.next;
                }
                pre.next = temp;
                cur = temp;
            } else {
                // 当遇到当前节点值和下一节点值不相等的节点时
                // pre和cur都移动到下一个节点接着遍历
                pre = pre.next;
                cur = cur.next;
            }
        }
        // 返回新的头结点
        return newHead.next;
    }

}
