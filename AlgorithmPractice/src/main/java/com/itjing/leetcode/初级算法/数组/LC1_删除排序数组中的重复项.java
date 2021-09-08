package com.itjing.leetcode.初级算法.数组;

/**
 * @author: lijing
 * @Date: 2021年08月25日 11:14
 * @Description: 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * <p>
 *  
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,2]
 * 输出：2, nums = [1,2]
 * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。
 * 不需要考虑数组中超出新长度后面的元素。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
 * 输出：5, nums = [0,1,2,3,4]
 * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。
 * 不需要考虑数组中超出新长度后面的元素。
 *  
 */
public class LC1_删除排序数组中的重复项 {

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(removeDuplicates(nums));
    }

    public static int removeDuplicates(int[] nums) {
       /*
           因为数组是排序的，只要是相同的肯定是挨着的，
           我们只需要遍历所有数组，然后前后两两比较，
           如果有相同的就把后面的给删除。

           使用双指针解决
           使用两个指针，右指针始终往右移动，

           1、如果右指针指向的值等于左指针指向的值，左指针不动。
           2、如果右指针指向的值不等于左指针指向的值，那么左指针往右移一步，然后再把右指针指向的值赋给左指针。
        */

        //边界条件判断
        if (nums == null || nums.length == 0)
            return 0;
        //左指针初始指向第一位
        int left = 0;
        //右指针初始指向第二位
        for (int right = 1; right < nums.length; right++)
            /*
            如果左指针和右指针指向的值一样，说明有重复的，这个时候，左指针不动，右指针继续往右移。
            如果右指针指向的值不等于左指针指向的值，那么左指针往右移一步，然后再把右指针指向的值赋给左指针。
            */
            if (nums[left] != nums[right])
                nums[++left] = nums[right];
        return ++left;
    }
}
