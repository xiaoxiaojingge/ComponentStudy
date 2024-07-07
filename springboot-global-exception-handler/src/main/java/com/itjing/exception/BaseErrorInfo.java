package com.itjing.exception;

/**
 * @author lijing
 * @date 2021年12月01日 16:37
 * @description 自定义异常基础接口类，自定义的异常枚举类需实现该接口
 */
public interface BaseErrorInfo {

	/**
	 * 获取错误码
	 * @return 错误码
	 */
	Integer getErrorCode();

	/**
	 * 获取错误信息
	 * @return 错误信息
	 */
	String getErrorMsg();

}
