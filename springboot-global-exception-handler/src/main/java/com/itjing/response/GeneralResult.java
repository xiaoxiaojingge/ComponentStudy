package com.itjing.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lijing
 * @date 2021年12月01日 16:30
 * @description
 */
@Getter
@Setter
public class GeneralResult<T> {

    private boolean success;
    private String errorCode;
    private String message;
    private T data;
    private String traceId;

    private GeneralResult(boolean success, T data, String message, String errorCode) {
        this.success = success;
        this.data = data;
        this.message = message;
        this.errorCode = errorCode;
    }

    public static <T> GeneralResult<T> genResult(boolean success, T data, String message) {
        return genResult(success, data, message, null);
    }

    public static <T> GeneralResult<T> genSuccessResult(T data) {
        return genResult(true, data, null, null);
    }

    public static <T> GeneralResult<T> genErrorResult(String message) {
        return genResult(false, null, message, null);
    }

    public static <T> GeneralResult<T> genSuccessResult() {
        return genResult(true, null, null, null);
    }

    public static <T> GeneralResult<T> genErrorResult(String message, String errorCode) {
        return genResult(false, null, message, errorCode);
    }

    public static <T> GeneralResult<T> genResult(boolean success, T data, String message,
                                                 String errorCode) {
        return new GeneralResult<>(success, data, message, errorCode);
    }

    public static <T> GeneralResult<T> genErrorResult(String message, String errorCode,
                                                      String traceId) {
        GeneralResult<T> result = genResult(false, null, message, errorCode);
        result.setTraceId(traceId);
        return result;
    }
}
