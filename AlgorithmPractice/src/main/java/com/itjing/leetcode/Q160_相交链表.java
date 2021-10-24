package com.itjing.leetcode;

/**
 * @author: lijing
 * @Date: 2021年09月20日 10:51
 * @Description: 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 */
public class Q160_相交链表 {

    public static void main(String[] args) {

    }

    /**
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
     * <p>
     * 思路：
     * 1、创建两个指针pA和pB，分别指向链表A和B头节点并遍历
     * 2、当pA遍历完链表A后，令pA = headB；当pB遍历完链表B后，令pB = headA
     * 3、当较长链表的指针指向较短链表的head时，两链表长度差已消除
     * 4、继续遍历，当pA==pB时，找到了相交节点
     * <p>
     * 题解：
     * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/solution/xiang-jiao-lian-biao-by-leetcode-solutio-a8jn/
     * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/solution/tu-jie-xiang-jiao-lian-biao-by-user7208t/
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA != null ? pA.next : headB;
            pB = pB != null ? pB.next : headA;
        }
        return pA;
    }
}
