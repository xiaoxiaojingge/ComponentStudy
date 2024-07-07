package com.itjing.study.函数式接口消灭if和else;

/**
 * @author lijing
 * @date 2022年06月01日 11:36
 * @description 定义抛出异常接口-函数式接口
 */
@FunctionalInterface
public interface ThrowExceptionFunction {

	/**
	 * 抛出异常信息
	 * @param message 异常信息
	 * @return void
	 **/
	void throwMessage(String message);

}
