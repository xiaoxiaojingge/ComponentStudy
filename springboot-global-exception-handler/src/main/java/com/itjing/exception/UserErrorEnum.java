package com.itjing.exception;

import lombok.Getter;

/**
 * @author lijing
 * @date 2021年12月01日 16:35
 * @description 自定义用户相关异常枚举类
 */
@Getter
public enum UserErrorEnum implements BaseErrorInfo {

	/**
	 * 用户不存在
	 */
	USER_NOT_FOUND(1001, "用户不存在!");

	private Integer errorCode;

	private String errorMsg;

	UserErrorEnum(Integer errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

}
