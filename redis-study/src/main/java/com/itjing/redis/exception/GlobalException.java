package com.itjing.redis.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lijing
 * @date 2022年05月26日 9:47
 * @description 全局异常捕获
 */
@RestControllerAdvice
public class GlobalException {

    /**
     * 业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public Map<String, Object> serviceException(ServiceException e) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", 500);
        map.put("message", e.getMessage());
        return map;
    }

}