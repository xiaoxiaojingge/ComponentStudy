package com.itjing.nowcoder;

import com.itjing.leetcode.ListNode;

/**
 * @author: lijing
 * @Date: 2021年09月20日 22:06
 * @Description: 判断给定的链表中是否有环。如果有环则返回true，否则返回false。
 */
public class NC4判断链表中是否有环 {


    public boolean hasCycle(ListNode head) {
        // 定义快慢指针，有环二者必会重合
        ListNode slow = head, fast = head;
        boolean flag = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // 快慢指针相遇
            if (slow == fast) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static void main(String[] args) {

    }
}
