package org.itjing.lambda;

import java.util.function.Consumer;

/**
 * @author lijing
 * @date 2021年12月09日 16:19
 * @description
 */
public class LambdaDemo12 {
    /**
     * 获取一个数组的和
     *
     * @param arr
     */
    public static void getSum(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        System.out.println("sum = " + sum);
    }

    public static void main(String[] args) {
        int[] arrs = {10, 19, -10, 0};

        // 使用方法引用：让这个指定的方法去重写接口的抽象方法，到时候调用接口的抽象方法就是调用传递过去的这个方法
        printSum(LambdaDemo12::getSum, arrs);

        Consumer<int[]> consumer = LambdaDemo12::getSum;
        consumer.accept(arrs);

    }

    // 有参无返回值，定义为消费型接口
    public static void printSum(Consumer<int[]> consumer, int[] arr) {
        consumer.accept(arr);
    }
}
