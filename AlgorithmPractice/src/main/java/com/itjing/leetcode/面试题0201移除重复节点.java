package com.itjing.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: lijing
 * @Date: 2021年09月20日 12:10
 * @Description: 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
 */
public class 面试题0201移除重复节点 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(1);
        head.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next = new ListNode(2);

        ListNode p = removeDuplicateNodes(null);
        while (p != null) {
            System.out.print(p.val + "\t");
            p = p.next;
        }
    }

    /**
     * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
     *
     * @param head
     * @return
     */
    public static ListNode removeDuplicateNodes(ListNode head) {
        // 当头节点不为空时执行以下逻辑
        if (head != null) {
            // 判断是否为重复节点
            Set<Integer> set = new HashSet<>();
            set.add(head.val);
            ListNode cur = head.next;
            ListNode node;
            while (cur != null) {
                // 节点是否重复
                if (set.contains(cur.val)) {
                    // 是否为最后一个节点，如果不是，则删除这个节点
                    if (cur.next != null) {
                        // 删除这个节点
                        cur.val = cur.next.val;
                        ListNode p = cur.next;
                        cur.next = p.next;
                    } else {
                        // 删除最后一个节点
                        node = head;
                        while (node.next.next != null) {
                            node = node.next;
                        }
                        node.next = null;
                        break;
                    }
                } else {
                    // 如果不重复，则添加到hash表中，接着看下一节点
                    set.add(cur.val);
                    cur = cur.next;
                }
            }
        }
        return head;
    }

}
