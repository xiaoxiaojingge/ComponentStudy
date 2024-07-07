package com.itjing.exception;

import lombok.Getter;

import javax.servlet.http.HttpServletResponse;

/**
 * @author lijing
 * @date 2021年12月01日 16:38
 * @description 自定义通用异常枚举类
 */
@Getter
public enum CommonErrorEnum implements BaseErrorInfo {

	/**
	 * 操作成功
	 */
	SUCCESS(HttpServletResponse.SC_OK, "Operation is Successful"),

	/**
	 * 业务异常
	 */
	FAILURE(HttpServletResponse.SC_BAD_REQUEST, "Biz Exception"),

	/**
	 * 未认证
	 */
	UN_AUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED, "Request Unauthorized"),

	/**
	 * 未找到该资源
	 */
	NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "404 Not Found"),

	MSG_NOT_READABLE(HttpServletResponse.SC_BAD_REQUEST, "Message Can't be Read"),

	METHOD_NOT_SUPPORTED(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Method Not Supported"),

	MEDIA_TYPE_NOT_SUPPORTED(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Media Type Not Supported"),

	REQ_REJECT(HttpServletResponse.SC_FORBIDDEN, "Request Rejected"),

	/**
	 * 服务器内部错误!
	 */
	INTERNAL_SERVER_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error"),

	PARAM_MISS(HttpServletResponse.SC_BAD_REQUEST, "Missing Required Parameter"),

	PARAM_TYPE_ERROR(HttpServletResponse.SC_BAD_REQUEST, "Parameter Type Mismatch"),

	PARAM_BIND_ERROR(HttpServletResponse.SC_BAD_REQUEST, "Parameter Binding Error"),

	PARAM_VALID_ERROR(HttpServletResponse.SC_BAD_REQUEST, "Parameter Validation Error"),

	/**
	 * 请求的数据格式不符!
	 */
	BODY_NOT_MATCH(HttpServletResponse.SC_BAD_REQUEST, "请求的数据格式不符!"),

	/**
	 * 服务器正忙，请稍后再试!
	 */
	SERVER_BUSY(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "服务器正忙，请稍后再试!");

	private Integer errorCode;

	private String errorMsg;

	CommonErrorEnum(Integer errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

}
