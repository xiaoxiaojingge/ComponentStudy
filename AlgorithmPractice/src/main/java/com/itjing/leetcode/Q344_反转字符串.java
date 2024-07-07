package com.itjing.leetcode;

/**
 * @author lijing
 * @date 2021年10月25日 11:10
 * @description 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 */
public class Q344_反转字符串 {

	public void reverseString(char[] s) {
		int left = 0;
		int right = s.length - 1;
		while (left < right) {
			// 交换 arr[left] 和 arr[right]
			char temp = s[left];
			s[left] = s[right];
			s[right] = temp;
			left++;
			right--;
		}
	}

}
