package com.itjing.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lijing
 * @date 2021年10月24日 19:42
 * @description 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0
 * 且不重复的三元组。
 */
public class Q15_三数之和 {

	public static void main(String[] args) {
		int[] nums = new int[] { -1, 0, 1, 2, -1, -4 };
		System.out.println(threeSum(nums));
	}

	public static List<List<Integer>> threeSum(int[] nums) {
		// 求和为 0 的三元组
		return threeSumTarget(nums, 0);
	}

	/**
	 * 计算数组 nums 中所有和为 target 的三元组
	 * @param nums
	 * @param target
	 * @return
	 */
	public static List<List<Integer>> threeSumTarget(int[] nums, int target) {
		// 数组得排个序
		Arrays.sort(nums);
		int n = nums.length;
		List<List<Integer>> res = new ArrayList<>();
		// 穷举 threeSum 的第一个数
		for (int i = 0; i < n; i++) {
			// 对 target - nums[i] 计算 twoSum
			List<List<Integer>> tuples = twoSumTarget(nums, i + 1, target - nums[i]);
			// 如果存在满足条件的二元组，再加上 nums[i] 就是结果三元组
			for (List<Integer> tuple : tuples) {
				tuple.add(nums[i]);
				res.add(tuple);
			}
			// 跳过第一个数字重复的情况，否则会出现重复结果
			while (i < n - 1 && nums[i] == nums[i + 1])
				i++;
		}
		return res;

	}

	/**
	 * 从 nums[start] 开始，计算有序数组nums 中所有和为 target 的二元组
	 * @param nums
	 * @param target
	 * @return
	 */
	public static List<List<Integer>> twoSumTarget(int[] nums, int start, int target) {
		// 先对数组排序
		Arrays.sort(nums);
		List<List<Integer>> res = new ArrayList<>();
		// 左指针改为从 start 开始，其他不变
		int left = start, right = nums.length - 1;
		while (left < right) {
			int sum = nums[left] + nums[right];
			// 记录索引 left 和 right 的最初值
			int leftNum = nums[left], rightNum = nums[right];
			// 根据 sum 和 target 的比较，移动左右指针
			if (sum < target) {
				while (left < right && nums[left] == leftNum)
					left++; // 让 sum 大一点
			}
			else if (sum > target) {
				while (left < right && nums[right] == rightNum)
					right--; // 让 sum 小一点
			}
			else {
				List<Integer> twoNum = new ArrayList<>();
				twoNum.add(nums[left]);
				twoNum.add(nums[right]);
				res.add(twoNum);
				// 跳过所有重复的元素
				while (left < right && nums[left] == leftNum)
					left++;
				while (left < right && nums[right] == rightNum)
					right--;
			}
		}
		return res;
	}

}
