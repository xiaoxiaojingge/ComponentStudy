package org.itjing.lambda;

/**
 * @author lijing
 * @date 2021年12月09日 14:32
 * @description 定义一个有参有返回值的函数式接口
 */
@FunctionalInterface
public interface Smokeable {

	/**
	 * 抽烟
	 * @param name
	 * @return
	 */
	int smoking(String name);

}
