package com.itjing.exception;

import com.itjing.exception.staffjoy.ResultCode;
import lombok.Getter;

/**
 * @author lijing
 * @date 2021年12月13日 9:44
 * @description
 */
public class PermissionDeniedException extends RuntimeException {

	@Getter
	private final ResultCode resultCode;

	public PermissionDeniedException(String message) {
		super(message);
		this.resultCode = ResultCode.UN_AUTHORIZED;
	}

	public PermissionDeniedException(ResultCode resultCode) {
		super(resultCode.getMsg());
		this.resultCode = resultCode;
	}

	public PermissionDeniedException(ResultCode resultCode, Throwable cause) {
		super(cause);
		this.resultCode = resultCode;
	}

	@Override
	public Throwable fillInStackTrace() {
		return this;
	}

}
