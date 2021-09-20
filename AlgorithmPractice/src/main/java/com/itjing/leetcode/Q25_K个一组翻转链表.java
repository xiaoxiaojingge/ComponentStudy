package com.itjing.leetcode;

/**
 * @author: lijing
 * @Date: 2021年09月18日 9:19
 * @Description:
 */
public class Q25_K个一组翻转链表 {

    public static void main(String[] args) {

    }

    /**
     * 反转以 a 为头结点的链表,循环实现
     *
     * @param a
     * @return
     */
    ListNode reverse(ListNode a) {
        ListNode pre, cur, nxt;
        pre = null;
        cur = a;
        nxt = a;
        while (cur != null) {
            nxt = cur.next;
            // 逐个结点反转
            cur.next = pre;
            // 更新指针位置
            pre = cur;
            cur = nxt;
        }
        // 返回反转后的头结点
        return pre;
    }


    /**
     * 「反转以 a 为头结点的链表」其实就是「反转 a 到 null 之间的结点」，那么如果让你「反转 a 到 b 之间的结点」，你会不会？
     * 只要更改函数签名，并把上面的代码中 null 改成 b 即可：
     */

    /**
     * 反转区间 [a, b) 的元素，注意是左闭右开
     *
     * @param a
     * @param b
     * @return
     */
    ListNode reverse(ListNode a, ListNode b) {
        ListNode pre, cur, nxt;
        pre = null;
        cur = a;
        nxt = a;
        // while 终止的条件改一下就行了
        while (cur != b) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        // 返回反转后的头结点
        return pre;
    }

    /**
     * 现在我们迭代实现了反转部分链表的功能，接下来就按照之前的逻辑编写 reverseKGroup 函数即可：
     * @param head
     * @param k
     * @return
     */
    public  ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        // 区间 [a, b) 包含 k 个待反转元素
        ListNode a, b;
        a = b = head;
        for (int i = 0; i < k; i++) {
            // 不足 k 个，不需要反转，base case
            if (b == null) return head;
            b = b.next;
        }
        // 反转前 k 个元素
        ListNode newHead = reverse(a, b);
        // 递归反转后续链表并连接起来
        a.next = reverseKGroup(b, k);
        return newHead;
    }

}
