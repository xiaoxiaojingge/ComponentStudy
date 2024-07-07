package com.itjing.leetcode;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: lijing
 * @Date: 2021年07月25日 16:22
 * @Description:
 *
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 * 示例 1： 输入：x = 123 输出：321
 *
 * 示例 2： 输入：x = -123 输出：-321
 *
 * 示例 3： 输入：x = 120 输出：21
 *
 * 示例 4： 输入：x = 0 输出：0
 *
 * 提示： -2^31 <= x <= 2^31 - 1
 *
 * 题解：https://leetcode-cn.com/problems/reverse-integer/solution/zheng-shu-fan-zhuan-by-leetcode-solution-bccn/
 */
@Slf4j
public class Q7_整数反转 {

	public static void main(String[] args) {
		log.info(Integer.toString(reverse(-123)));
	}

	public static int reverse(int x) {
		int rev = 0;
		while (x != 0) {
			// 如果反转后整数超过 32 位的有符号整数的范围 [−2^31, 2^31 − 1] ，就返回 0。
			// 题目中不许用64位整数，经过一系列证明，当 rev 在 [−2^31 / 10 , 2^31 − 1 / 10] 之间时成立。
			if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
				return 0;
			}
			int digit = x % 10;
			x /= 10;
			rev = rev * 10 + digit;
		}
		return rev;
	}

}
