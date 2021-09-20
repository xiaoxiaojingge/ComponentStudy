package com.itjing.leetcode;

/**
 * @author: lijing
 * @Date: 2021年09月20日 19:56
 * @Description: 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点。传入函数的唯一参数为 要被删除的节点 。
 * 链表至少包含两个节点。
 * 链表中所有节点的值都是唯一的。
 * 给定的节点为非末尾节点并且一定是链表中的一个有效节点。
 * 不要从你的函数中返回任何结果。
 */
public class Q237_删除链表中的节点 {

    /**
     * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点。传入函数的唯一参数为 要被删除的节点 。
     *
     * @param node
     */
    public void deleteNode(ListNode node) {
        // 将后者值赋给当前，即当前节点伪装成后继节点
        node.val = node.next.val;
        // 后面连接后继节点的后继节点
        node.next = node.next.next;
    }
}
