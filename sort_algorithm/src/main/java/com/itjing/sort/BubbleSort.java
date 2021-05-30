package com.itjing.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author: lijing
 * @Date: 2021年05月28日 8:45
 * @Description: 冒泡排序
 */
@Slf4j
public class BubbleSort {
    /**
     * 算法思想：
     * <p>
     * 1、比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * 2、对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
     * 3、针对所有的元素重复以上的步骤，除了最后一个。
     * 4、持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     */

    public static void main(String[] args) {
        int[] a = {2, 6, 3, 78, 43, 1};
        bubbleSort(a, a.length);
        log.info(Arrays.toString(a));
    }

    public static void bubbleSort(int a[], int n) {
        for (int i = 0; i < n - 1; ++i) {
            for (int j = 0; j < n - i - 1; ++j) {
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];  //交换
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    log.info(Arrays.toString(a));
                }
            }
        }
    }
}
