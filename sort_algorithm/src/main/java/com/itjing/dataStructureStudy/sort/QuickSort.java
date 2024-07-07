package com.itjing.dataStructureStudy.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author: lijing
 * @Date: 2021年05月28日 9:44
 * @Description: 快速排序
 */
@Slf4j
public class QuickSort {

	/**
	 * 算法思想：
	 *
	 * 1、选取第一个数为基准 2、将比基准小的数交换到前面，比基准大的数交换到后面 3、对左右区间重复第二步，直到各区间只有一个数
	 */

	/**
	 * 从数列中挑出一个元素，称为 “基准”（pivot）； 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
	 * 在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
	 * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。 最佳情况：T(n) = O(nlogn) 最差情况：T(n) = O(n2)
	 * 平均情况：T(n) = O(nlogn)
	 */

	public static void main(String[] args) {
		int[] a = { 2, 6, 3, 78, 43, 1 };
		QuickSort(a, 0, a.length - 1);
		log.info("最终排序的结果：{}", Arrays.toString(a));
	}

	/**
	 * 快速排序算法
	 * @param array
	 * @param low
	 * @param hight
	 */
	public static void QuickSort(int[] array, int low, int hight) {
		if (array.length < 1 || low < 0 || hight >= array.length || low > hight)
			return;
		if (low < hight) {
			int privotpos = partition(array, low, hight);
			QuickSort(array, low, privotpos - 1);
			QuickSort(array, privotpos + 1, hight);
		}
	}

	public static int partition(int[] array, int low, int hight) {
		int privot = array[low];
		while (low < hight) {
			while (low < hight && array[hight] >= privot)
				--hight;
			array[low] = array[hight];
			while (low < hight && array[low] <= privot)
				++low;
			array[hight] = array[low];
		}
		array[low] = privot;
		return low;
	}

}
