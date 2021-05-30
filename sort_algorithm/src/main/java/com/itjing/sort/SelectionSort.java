package com.itjing.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author: lijing
 * @Date: 2021年05月28日 9:08
 * @Description: 选择排序
 */
@Slf4j
public class SelectionSort {
    /**
     * 算法思想：
     * <p>
     * 1、在未排序序列中找到最小（大）元素，存放到排序序列的起始位置
     * 2、从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾
     * 3、以此类推，直到所有元素均排序完毕
     */
    public static void main(String[] args) {
        int[] a = {2, 6, 3, 78, 43, 1};
        int[] arr = selectionSort(a);
        log.info("最终排序的结果：{}", Arrays.toString(arr));
    }

    public static int[] selectionSort(int[] arr) {
        log.info("要排序的数组为：{}", Arrays.toString(arr));
        int len = arr.length;
        int minIndex, temp;
        for (int i = 0; i < len - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[minIndex]) {     // 寻找最小的数
                    minIndex = j;                 // 将最小数的索引保存
                }
            }
            log.info("目前最小数的索引是：{}, 它的值是 {}", minIndex, arr[minIndex]);
            temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
            log.info("此轮排序后，结果是：{}\n", Arrays.toString(arr));
        }
        return arr;
    }
}
