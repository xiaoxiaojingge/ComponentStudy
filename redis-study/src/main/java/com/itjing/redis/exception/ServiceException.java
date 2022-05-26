package com.itjing.redis.exception;

/**
 * @author lijing
 * @date 2022年05月26日 9:44
 * @description
 */
public class ServiceException extends Exception {

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }
}
