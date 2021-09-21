package com.itjing.nowcoder;

import com.itjing.leetcode.ListNode;
import com.itjing.util.ListNodeUtil;

/**
 * @author: lijing
 * @Date: 2021年09月18日 18:16
 * @Description: 删除有序链表中重复的元素
 */
public class NC25删除有序链表中重复的元素I {


    public static void main(String[] args) {

        ListNode head = ListNodeUtil.generateListNode();
        ListNode p = deleteDuplicates(head);
        while (p != null) {
            System.out.print(p.val + "\t");
            p = p.next;
        }

    }

    /**
     * 删除有序链表中重复的元素
     *
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        // 使用双指针
        // 初始左指针在第一个节点，右指针在第二个节点
        ListNode left = head, right = head.next;
        while (right != null) {
            if (left.val == right.val) {

            } else {
                // 遇到不相同值，则将值往前放到左指针的下一个，然后左指针指向它
                left.next = right;
                left = left.next;
            }
            right = right.next;
        }
        // 这时left之前就是不重复序列，后者置空
        left.next = null;
        return head;
    }

}
