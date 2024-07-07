package org.itjing.lambda;

import java.util.Arrays;
import java.util.function.Supplier;

/**
 * @author lijing
 * @date 2021年12月09日 15:56
 * @description
 */
public class LambdaDemo6 {

	public static void main(String[] args) {
		int[] arr = { 22, 65, 100, -2 };
		printMax(() -> {
			Arrays.sort(arr);
			return arr[arr.length - 1];
		});
	}

	public static void printMax(Supplier<Integer> supplier) {
		Integer max = supplier.get();
		System.out.println("max = " + max);
	}

}
