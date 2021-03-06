package com.itjing.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lijing
 * @date 2021年10月24日 20:28
 * @description 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]]
 */
public class Q18_四数之和 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        System.out.println(fourSum2(nums, 0));
    }

    /**
     * 计算数组 nums 中所有和为 target 的四元组
     *
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        // 数组得排个序
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        // 穷举 threeSum 的第一个数
        // i 从 start 开始穷举，其他都不变
        for (int i = 0; i < n; i++) {
            // 对 target - nums[i] 计算 twoSum
            List<List<Integer>> tuples = threeSumTarget(nums, i + 1, target - nums[i]);
            // 如果存在满足条件的三元组，再加上 nums[i] 就是结果四元组
            for (List<Integer> tuple : tuples) {
                tuple.add(nums[i]);
                res.add(tuple);
            }
            // fourSum 的第一个数不能重复
            while (i < n - 1 && nums[i] == nums[i + 1])
                i++;
        }
        return res;
    }


    /**
     * 从 nums[start] 开始，计算数组 nums 中所有和为 target 的三元组
     *
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> threeSumTarget(int[] nums, int start, int target) {
        // 数组得排个序
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        // 穷举 threeSum 的第一个数
        // i 从 start 开始穷举，其他都不变
        for (int i = start; i < n; i++) {
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
     *
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
            } else if (sum > target) {
                while (left < right && nums[right] == rightNum)
                    right--; // 让 sum 小一点
            } else {
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




    /**
     * 计算数组 nums 中所有和为 target 的四元组
     *
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> fourSum2(int[] nums, int target) {
        // 数组得排个序
        Arrays.sort(nums);
        // n 为 4，从 nums[0] 开始计算和为 target 的四元组
        return nSumTarget(nums, 4, 0, target);
    }


    /**
     * 注意：调用这个函数之前一定要先给 nums 排序
     *
     * @param nums
     * @param n
     * @param start
     * @param target
     * @return
     */
    public static List<List<Integer>> nSumTarget(
            int[] nums, int n, int start, int target) {
        int sz = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        // 至少是 2Sum，且数组大小不应该小于 n
        if (n < 2 || sz < n) return res;
        // 2Sum 是 base case
        if (n == 2) {
            // 双指针那一套操作
            int lo = start, hi = sz - 1;
            while (lo < hi) {
                int sum = nums[lo] + nums[hi];
                int left = nums[lo], right = nums[hi];
                if (sum < target) {
                    while (lo < hi && nums[lo] == left) lo++;
                } else if (sum > target) {
                    while (lo < hi && nums[hi] == right) hi--;
                } else {
                    List<Integer> twoNum = new ArrayList<>();
                    twoNum.add(left);
                    twoNum.add(right);
                    res.add(twoNum);
                    while (lo < hi && nums[lo] == left) lo++;
                    while (lo < hi && nums[hi] == right) hi--;
                }
            }
        } else {
            // n > 2 时，递归计算 (n-1)Sum 的结果
            for (int i = start; i < sz; i++) {
                List<List<Integer>> sub = nSumTarget(nums, n - 1, i + 1, target - nums[i]);
                for (List<Integer> arr : sub) {
                    // (n-1)Sum 加上 nums[i] 就是 nSum
                    arr.add(nums[i]);
                    res.add(arr);
                }
                while (i < sz - 1 && nums[i] == nums[i + 1]) i++;
            }
        }
        return res;
    }
}
