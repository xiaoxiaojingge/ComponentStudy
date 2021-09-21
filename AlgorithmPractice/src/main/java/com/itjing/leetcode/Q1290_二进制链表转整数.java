package com.itjing.leetcode;

import com.itjing.util.ListNodeUtil;

/**
 * @author: lijing
 * @Date: 2021年09月20日 18:41
 * @Description: 给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。
 * <p>
 * 请你返回该链表所表示数字的 十进制值 。
 */
public class Q1290_二进制链表转整数 {

    /**
     * 给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。
     * 请你返回该链表所表示数字的 十进制值 。
     *
     * @param head
     * @return
     */
    public static int getDecimalValue(ListNode head) {
        //反转链表
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        // 求十进制值
        int i = 0;
        int sum = 0;
        while (pre != null) {
            if (pre.val == 1) {
                sum = sum + (int) Math.pow(2, i);
            }
            pre = pre.next;
            i++;
        }
        return sum;
    }

    /**
     * 反推  例如5的余数 0101，反着推回去（（0x2+1）x2 + 0） x2 +1，索引是ans*2 + cur.val
     * 5÷2=2余1
     * 2÷2=1余0
     * 1÷2=0余1
     * ===> 得出二进制 101 .
     * 反推回去 商 x 除数 + 余数
     * => 0 x 2 + 1 = 1
     * -> 1 x 2 + 0 = 2
     * -> 2 x 2 +1 = 5
     *
     * @param head
     * @return
     */
    public static int getDecimalValue2(ListNode head) {
        ListNode curNode = head;
        int ans = 0;
        while (curNode != null) {
            ans = ans * 2 + curNode.val;
            curNode = curNode.next;
        }
        return ans;
    }

    public static void main(String[] args) {
        ListNode head = ListNodeUtil.generateListNode();

        System.out.println(getDecimalValue(head));
    }
}
