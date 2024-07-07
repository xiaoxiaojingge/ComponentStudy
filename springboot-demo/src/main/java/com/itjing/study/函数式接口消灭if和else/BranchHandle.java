package com.itjing.study.函数式接口消灭if和else;

/**
 * @author lijing
 * @date 2022年06月01日 11:39
 * @description 分支处理接口
 */
@FunctionalInterface
public interface BranchHandle {

	/**
	 * 分支操作
	 * @param trueHandle 为true时要进行的操作
	 * @param falseHandle 为false时要进行的操作
	 * @return void
	 **/
	void trueOrFalseHandle(Runnable trueHandle, Runnable falseHandle);

}
