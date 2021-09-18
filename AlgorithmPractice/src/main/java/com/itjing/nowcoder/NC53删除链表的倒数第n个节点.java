package com.itjing.nowcoder;

/**
 * @author: lijing
 * @Date: 2021年09月18日 18:02
 * @Description: 删除链表的倒数第n个节点
 */
public class NC53删除链表的倒数第n个节点 {
    static class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode p = removeNthFromEnd(head, 2);
        while (p != null) {
            System.out.print(p.val + "\t");
            p = p.next;
        }

    }

    /**
     * 删除链表的倒数第n个节点
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p = head;
        int sum = 0;
        while (p != null) {
            sum++;
            p = p.next;
        }
        // 如果倒数第n个节点是头结点
        if (sum == n) {
            return head.next;
        }
        ListNode pre = head, cur = head;
        for (int i = 0; i < sum - n; i++) {
            pre = cur;
            cur = cur.next;
        }
        // 断开当前结点，将此节点之前结点与后结点相连
        pre.next = cur.next;
        return head;
    }
}
