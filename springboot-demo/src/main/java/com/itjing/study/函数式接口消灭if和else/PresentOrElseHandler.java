package com.itjing.study.函数式接口消灭if和else;

import java.util.function.Consumer;

/**
 * @author lijing
 * @date 2022年06月01日 11:45
 * @description 空值与非空值分支处理
 */
public interface PresentOrElseHandler<T extends Object> {

	/**
	 * 值不为空时执行消费操作 值为空时执行其他的操作
	 * @param action 值不为空时，执行的消费操作
	 * @param emptyAction 值为空时，执行的操作
	 * @return void
	 **/
	void presentOrElseHandle(Consumer<? super T> action, Runnable emptyAction);

}
