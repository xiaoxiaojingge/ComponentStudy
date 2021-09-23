package com.itjing.dataStructureStudy.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author: lijing
 * @Date: 2021年05月28日 9:20
 * @Description:
 */
@Slf4j
public class InsertSort {
    /**
     * 算法思想：
     * <p>
     * 1、从第一个元素开始,该元素可以认为已经被排序
     * 2、取出下一个元素,在已经排序的元素序列中从后向前扫描
     * 3、如果该元素（已排序）大于新元素,将该元素移到下一位置
     * 4、重复步骤3,直到找到已排序的元素小于或者等于新元素的位置
     * 5、将新元素插入到该位置后
     * 6、重复步骤2~5
     */

    public static void main(String[] args) {
        int[] a = {2, 6, 3, 78, 43, 1};
        insertSort(a);
        log.info("最终排序的结果：{}", Arrays.toString(a));
    }

    /**
     * 插入排序
     *
     * @param array
     * @return
     */
    public static void insertSort(int[] array) {
        if (array.length > 0) {
            for (int i = 0; i < array.length - 1; i++) {
                int current = array[i + 1];
                int index = i;
                while (index >= 0 && current < array[index]) {
                    array[index + 1] = array[index];
                    index--;
                }
                array[index + 1] = current; // 插入到正确位置
                log.info("第{}次排序的结果：{}", i + 1, Arrays.toString(array));     //打印每趟排序的结果
            }
        }
    }
}
