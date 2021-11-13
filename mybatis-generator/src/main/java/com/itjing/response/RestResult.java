package com.itjing.response;

import java.io.Serializable;

/**
 * @author lijing
 * @date 2021年11月13日 9:32
 * @description
 */
public class RestResult<T> extends InfoMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private T data;

    public RestResult() {
    }

    public RestResult(int code, String message, T data) {
        super.setReturnCode(String.valueOf(code));
        super.setReturnMsg(message);
        this.data = data;
    }

    public RestResult(int code, T data) {
        super.setReturnCode(String.valueOf(code));
        this.data = data;
    }

    public RestResult(int code, String message) {
        super.setReturnCode(String.valueOf(code));
        super.setReturnMsg(message);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }



    @Override
    public String toString() {
        return "RestResult{" +
                "code=" + super.getReturnCode() +
                ", message='" + super.getReturnMsg() + '\'' +
                ", data=" + data +
                '}';
    }

    public static <T> ResResultBuilder<T> builder() {
        return new ResResultBuilder<T>();
    }

    public static final class ResResultBuilder<T> {
        private int code;
        private String errMsg;
        private T data;

        private ResResultBuilder() {
        }

        public ResResultBuilder<T> withCode(int code) {
            this.code = code;
            return this;
        }

        public ResResultBuilder<T> withMsg(String errMsg) {
            this.errMsg = errMsg;
            return this;
        }

        public ResResultBuilder<T> withData(T data) {
            this.data = data;
            return this;
        }

        public RestResult<T> build() {
            RestResult<T> restResult = new RestResult<T>();
            restResult.setReturnCode(String.valueOf(code));
            restResult.setReturnMsg(errMsg);
            restResult.setData(data);
            return restResult;
        }
    }
}
