package org.itjing.lambda;

import java.util.Date;
import java.util.function.Supplier;

/**
 * @author lijing
 * @date 2021年12月09日 16:26
 * @description
 */
public class LambdaDemo13 {

	public static void main(String[] args) {
		// 使用Lambda表达式获取当前秒数
		Date date = new Date();
		Supplier<Long> supplier = () -> date.getTime();
		System.out.println(supplier.get());
		// 使用方法引用简化上述代码
		supplier = date::getTime;
		System.out.println(supplier.get());
	}

}
