package com.itjing.dataStructureStudy.search;

/**
 * @author: lijing
 * @Date: 2021年09月23日 10:58
 * @Description: 插值查找（要求数组是有序的） 插值查找算法类似于二分查找，不同的是插值查找每次从 自适应mid 处开始查找 插值索引公式： int mid =
 * left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left])
 */
public class _3_InsertValueSearch {

	public static void main(String[] args) {

		// int [] arr = new int[100];
		// for(int i = 0; i < 100; i++) {
		// arr[i] = i + 1;
		// }

		int arr[] = { 1, 8, 10, 89, 1000, 1000, 1234 };

		int index = insertValueSearch(arr, 0, arr.length - 1, 1234);
		System.out.println("index = " + index);
		System.out.println("插值查找次数-->" + count);

		// System.out.println(Arrays.toString(arr));
	}

	// 编写插值查找算法
	// 说明：插值查找算法，也要求数组是有序的
	static int count = 0;

	/**
	 * @param arr 数组
	 * @param left 左边索引
	 * @param right 右边索引
	 * @param findVal 查找值
	 * @return 如果找到，就返回对应的下标，如果没有找到，返回-1
	 */
	public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
		count++;
		// 注意：findVal < arr[0] 和 findVal > arr[arr.length - 1] 必须需要
		// 否则我们得到的 mid 可能越界
		if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
			return -1;
		}

		// 求出mid, 自适应
		int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
		int midVal = arr[mid];
		if (findVal > midVal) { // 说明应该向右边递归
			return insertValueSearch(arr, mid + 1, right, findVal);
		}
		else if (findVal < midVal) { // 说明向左递归查找
			return insertValueSearch(arr, left, mid - 1, findVal);
		}
		else {
			return mid;
		}

	}

}
