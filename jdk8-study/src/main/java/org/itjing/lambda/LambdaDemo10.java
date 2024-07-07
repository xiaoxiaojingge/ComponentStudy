package org.itjing.lambda;

import java.util.function.Predicate;

/**
 * @author lijing
 * @date 2021年12月09日 16:08
 * @description
 */
public class LambdaDemo10 {

	// 使用Lambda判断一个名称，如果名称超过3个字就认为是很长的名称
	public static void main(String[] args) {
		test((name) -> {
			return name.length() > 3;
		}, "迪丽热巴");
	}

	public static void test(Predicate<String> predicate, String name) {
		boolean test = predicate.test(name);
		if (test) {
			System.out.println(name + "的名称很长");
		}
		else {
			System.out.println(name + "的名称不长");
		}
	}

}
