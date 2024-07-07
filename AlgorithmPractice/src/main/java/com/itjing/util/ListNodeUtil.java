package com.itjing.util;

import com.itjing.leetcode.ListNode;

import java.util.Scanner;

/**
 * @author: lijing
 * @Date: 2021年09月21日 7:56
 * @Description: 链表输入工具类
 */
public class ListNodeUtil {

	/**
	 * 输入单链表
	 * @return
	 */
	public static ListNode generateListNode() {
		Scanner sc = new Scanner(System.in);
		ListNode node = new ListNode(0);
		ListNode head = node;
		String input = sc.nextLine();
		String[] split = input.split("\\s");
		for (String s : split) {
			node.next = new ListNode(Integer.parseInt(s));
			node = node.next;
		}
		sc.close();
		return head.next;
	}

	public static void print(ListNode p) {
		while (p != null) {
			System.out.print(p.val + "\t");
			p = p.next;
		}
	}

	public static void main(String[] args) {
		ListNode p = generateListNode();
		while (p != null) {
			System.out.print(p.val + "\t");
			p = p.next;
		}
	}

}
