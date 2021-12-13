package com.itjing.exception;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.MDC;

/**
 * @author lijing
 * @date 2021年12月01日 16:36
 * @description 自定义业务异常类
 */
@Getter
@Setter
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    // 错误码
    private Integer errorCode;
    // 错误信息
    private String errorMsg;
    // 日志追踪ID
    private String traceId = MDC.get("traceId");

    public BizException(BaseErrorInfo errorInfo) {
        super(errorInfo.getErrorMsg());
        this.errorCode = errorInfo.getErrorCode();
        this.errorMsg = errorInfo.getErrorMsg();
    }

    public BizException(BaseErrorInfo errorInfo, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorInfo.getErrorCode();
        this.errorMsg = errorMsg;
    }

    public BizException(BaseErrorInfo errorInfo, Throwable cause) {
        super(errorInfo.getErrorMsg(), cause);
        this.errorCode = errorInfo.getErrorCode();
        this.errorMsg = errorInfo.getErrorMsg();
    }

    public BizException(Integer errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BizException(Integer errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    /**
     * 自定义异常如何不打印异常堆栈
     * 重写以下方法
     *
     * @return
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
