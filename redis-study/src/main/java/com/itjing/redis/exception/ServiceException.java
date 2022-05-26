package com.itjing.redis.exception;

/**
 * @author lijing
 * @date 2022年05月26日 9:44
 * @description
 */
public class ServiceException extends Exception {

    private Integer code = 500;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Integer code) {
        super(message);
        this.code = code;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
