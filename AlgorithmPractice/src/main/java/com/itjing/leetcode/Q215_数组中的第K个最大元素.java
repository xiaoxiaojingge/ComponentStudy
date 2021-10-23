package com.itjing.leetcode;

/**
 * @author lijing
 * @date 2021年10月23日 9:52
 * @description 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 */
public class Q215_数组中的第K个最大元素 {
    public static void main(String[] args) {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        /*quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));*/
        System.out.println(findKthLargest(nums, 2));
    }

    public static void quickSort(int[] nums, int left, int right) {
        int l = left; //左下标
        int r = right; //右下标
        //pivot 中轴值
        //left + (right - left) / 2 防止溢出
        //取中间值作为基准值
        int pivot = nums[left + (right - left) / 2];
        int temp = 0; //临时变量，作为交换时使用
        while (l < r) {
            //在pivot的左边一直找,找到大于等于pivot值,才退出
            while (nums[l] < pivot) {
                l += 1;
            }
            //在pivot的右边一直找,找到小于等于pivot值,才退出
            while (nums[r] > pivot) {
                r -= 1;
            }
            // 如果l == r说明 pivot 的左右两边的值，
            // 已经按照左边全部是小于等于pivot值，右边全部是大于等于pivot值
            // 这时退出循环
            if (l == r) {
                break;
            }
            // 如果，未满足上面的条件，则交换，即将小值放基准值左边，大的放右边
            temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;

            // 如果不加这两个if判断的话，可能由于两者同时和基准相同，并且，l和r不相等
            // 造成 arr[l] 和 arr[r] 位置不动，一直交换，出现死循环
            // 所以要让它们位置动，为的就是当两者同时和基准值相同时，不一直交换
            // 可以自己debug看看
            // 如果交换完后，发现这个arr[l] == pivot值 相等 r--，前移
            if (nums[l] == pivot) {
                r -= 1;
            }
            //如果交换完后，发现这个arr[r] == pivot值 相等 l++，后移
            if (nums[r] == pivot) {
                l += 1;
            }
        }
        // 如果 l == r, 必须l++, r--, 否则为出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        if (left < r) {
            quickSort(nums, left, r);
        }
        if (right > l) {
            quickSort(nums, l, right);
        }
    }

    public static int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        quickSort(nums, 0, len - 1);
        return nums[len - k];
    }
}
