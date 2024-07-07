package org.itjing.lambda;

import java.util.function.Function;

/**
 * @author lijing
 * @date 2021年12月09日 16:31
 * @description
 */
public class LambdaDemo15 {

	public static void main(String[] args) {
		// 使用Lambda表达式将字符串转换为Long类型
		Function<String, Long> function = (s) -> Long.parseLong(s);

		Long apply = function.apply("5");
		System.out.println("apply = " + apply);

		// 使用Lambda简化上面的代码
		function = Long::parseLong;
		apply = function.apply("5");
		System.out.println("apply = " + apply);
	}

}
