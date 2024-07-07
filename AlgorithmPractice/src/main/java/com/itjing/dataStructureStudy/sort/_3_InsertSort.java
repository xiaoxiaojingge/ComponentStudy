package com.itjing.dataStructureStudy.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: lijing
 * @Date: 2021年09月22日 16:34
 * @Description: 插入排序（形象的说法就是打扑克，理牌顺序） 牌堆就是一个无序列表，手里拿着的就是有序列表，每次抓牌的时候都会将牌插入到对应位置，使其有序
 */
public class _3_InsertSort {

	public static void main(String[] args) {
		// int[] arr = {101, 34, 119, 1, -1, 89};
		// 创建要给80000个的随机的数组
		int[] arr = new int[80000];
		for (int i = 0; i < 80000; i++) {
			arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
		}

		System.out.println("插入排序前");
		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(date1);
		System.out.println("排序前的时间是=" + date1Str);

		insertSort(arr); // 调用插入排序算法

		Date date2 = new Date();
		String date2Str = simpleDateFormat.format(date2);
		System.out.println("排序后的时间是=" + date2Str);

		// System.out.println(Arrays.toString(arr));
	}

	/*
	 *
	 *
	 * //使用逐步推导的方式来讲解，便利理解
	 *
	 * //第1轮 {101, 34, 119, 1}; => {34, 101, 119, 1}
	 *
	 * //定义待插入的数 int insertVal = arr[1]; int insertIndex = 1 - 1; //即arr[1]的前面这个数的下标
	 *
	 * //给insertVal 找到插入的位置 //说明 //1. insertIndex >= 0 保证在给insertVal 找插入位置，不越界 //2.
	 * insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置 //3. 就需要将 arr[insertIndex] 后移 {101,
	 * 34, 119, 1}; => {101,101,119,1} while(insertIndex >= 0 && insertVal <
	 * arr[insertIndex] ) { arr[insertIndex + 1] = arr[insertIndex];// arr[insertIndex]
	 * insertIndex--; } //当退出while循环时，说明插入的位置找到, insertIndex + 1 //举例：理解不了，我们一会 debug
	 * arr[insertIndex + 1] = insertVal;
	 *
	 * System.out.println("第1轮插入"); System.out.println(Arrays.toString(arr));
	 *
	 * //第2轮 {34, 101, 119, 1}; => {34, 101, 119, 1} insertVal = arr[2]; insertIndex = 2 -
	 * 1;
	 *
	 * while(insertIndex >= 0 && insertVal < arr[insertIndex] ) { arr[insertIndex + 1] =
	 * arr[insertIndex];// arr[insertIndex] insertIndex--; }
	 *
	 * arr[insertIndex + 1] = insertVal; System.out.println("第2轮插入");
	 * System.out.println(Arrays.toString(arr));
	 *
	 *
	 * //第3轮 {34, 101, 119, 1}; => {1, 34, 101, 119} insertVal = arr[3]; insertIndex = 3 -
	 * 1;
	 *
	 * while (insertIndex >= 0 && insertVal < arr[insertIndex]) { arr[insertIndex + 1] =
	 * arr[insertIndex];// arr[insertIndex] insertIndex--; }
	 *
	 * arr[insertIndex + 1] = insertVal; System.out.println("第3轮插入");
	 * System.out.println(Arrays.toString(arr));
	 */

	// 插入排序
	public static void insertSort(int[] arr) {
		int insertVal = 0;
		int insertIndex = 0;
		// 使用for循环来把代码简化
		for (int i = 1; i < arr.length; i++) {
			// 定义待插入的数
			insertVal = arr[i];
			insertIndex = i - 1; // 即arr[i]的前面这个数的下标

			// 给insertVal 找到插入的位置
			// 说明
			// 1. insertIndex >= 0 保证在给insertVal 找插入位置，不越界
			// 2. insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
			// 3. 就需要将 arr[insertIndex] 后移
			// 此处 < 改为 > 即可从大到小排序
			while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
				arr[insertIndex + 1] = arr[insertIndex];// arr[insertIndex]
				insertIndex--;
			}
			// 当退出while循环时，说明插入的位置找到, insertIndex + 1
			// 举例：理解不了，我们一会 debug
			// 这里我们判断是否需要赋值，即可能这个值比之前的都大，找不到插入位置，无需调整位置
			if (insertIndex + 1 != i) {
				arr[insertIndex + 1] = insertVal;
			}

			// System.out.println("第"+i+"轮插入");
			// System.out.println(Arrays.toString(arr));
		}

	}

}
