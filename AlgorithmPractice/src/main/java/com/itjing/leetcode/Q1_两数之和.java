package com.itjing.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author: lijing
 * @Date: 2021年07月25日 12:05
 * @Description: 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出和为目标值 target 的那两个整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 * <p>
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * <p>
 * 示例 2：
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * <p>
 * 示例 3：
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *  
 * 提示：
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 */
@Slf4j
public class Q1_两数之和 {
    public static void main(String[] args) {
       /* int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        log.info(Arrays.toString(twoSum(nums, target)));*/

        int[] nums = new int[]{1, 3, 1, 2, 2, 3};
        System.out.println(twoSumTarget(nums, 4));
    }

    public static int[] twoSum(int[] nums, int target) {
        // 利用哈希表
        // 这样我们创建一个哈希表，对于每一个 x，我们首先查询哈希表中是否存在 target - x，
        // 然后将 x 插入到哈希表中，即可保证不会让 x 和自己匹配。
        Map<Integer, Integer> map = new HashMap<>(16);
        for (int i = 0; i < nums.length; i++) {
            // 如果map中存在target - x，返回其索引和x的索引
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            // 将此数作为key，索引作为value存入哈希表
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }


    public static List<List<Integer>> twoSumTarget(int[] nums, int target) {
        // 先对数组排序
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int left = 0, right = nums.length - 1;
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
}
