package com.itjing.dataStructureStudy.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: lijing
 * @Date: 2021年09月22日 18:06
 * @Description: 快速排序
 */
public class _5_QuickSort {

	public static void main(String[] args) {
		// int[] arr = {-9,78,0,23,-567,70, -1,900, 4561};

		// 测试快排的执行速度
		// 创建要给80000个的随机的数组
		int[] arr = new int[8000000];
		for (int i = 0; i < 8000000; i++) {
			arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
		}

		System.out.println("排序前");
		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(date1);
		System.out.println("排序前的时间是=" + date1Str);

		quickSort(arr, 0, arr.length - 1);

		Date date2 = new Date();
		String date2Str = simpleDateFormat.format(date2);
		System.out.println("排序后的时间是=" + date2Str);
		// System.out.println("arr=" + Arrays.toString(arr));
	}

	public static void quickSort(int[] arr, int left, int right) {
		int l = left; // 左下标
		int r = right; // 右下标
		// pivot 中轴值
		// left + (right - left) / 2 防止溢出
		// 取中间值作为基准值
		int pivot = arr[left + (right - left) / 2];
		int temp = 0; // 临时变量，作为交换时使用
		// while循环的目的是让比pivot值小放到左边，比pivot值大放到右边
		while (l < r) {
			// 在pivot的左边一直找,找到大于等于pivot值,才退出
			while (arr[l] < pivot) {
				l += 1;
			}
			// 在pivot的右边一直找,找到小于等于pivot值,才退出
			while (arr[r] > pivot) {
				r -= 1;
			}
			// 如果l == r说明pivot 的左右两的值，
			// 已经按照左边全部是小于等于pivot值，右边全部是大于等于pivot值
			if (l == r) {
				break;
			}

			// 交换
			temp = arr[l];
			arr[l] = arr[r];
			arr[r] = temp;

			// 如果不加这两个if判断的话，可能由于两者同时和基准相同，并且，l和r不相等
			// 造成 arr[l] 和 arr[r] 位置不动，一直交换，出现死循环
			// 所以要让它们位置动，为的就是当两者同时和基准值相同时，不一直交换
			// 可以自己debug看看
			// 如果交换完后，发现这个arr[l] == pivot值 相等 r--，前移
			if (arr[l] == pivot) {
				r -= 1;
			}
			// 如果交换完后，发现这个arr[r] == pivot值 相等 l++，后移
			if (arr[r] == pivot) {
				l += 1;
			}
		}

		// 如果 l == r, 必须l++, r--, 否则为出现栈溢出
		if (l == r) {
			l += 1;
			r -= 1;
		}

		// 向左递归
		if (left < r) {
			quickSort(arr, left, r);
		}
		// 向右递归
		if (right > l) {
			quickSort(arr, l, right);
		}
	}

}
