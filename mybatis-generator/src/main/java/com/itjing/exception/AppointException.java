package com.itjing.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author lijing
 * @date 2021年11月14日 10:38
 * @description 自定义异常
 */
@Getter
@Setter
@NoArgsConstructor
public class AppointException extends RuntimeException {
    private Integer code;

    public AppointException(Integer code, String message) {
        super(message);
        this.setCode(code);
    }

    public AppointException(String message) {
        super(message);
    }

    /**
     * 指定 code和 message
     *
     * @param code
     * @param message
     * @return
     */
    public static AppointException errorMessage(Integer code, String message) {
        return new AppointException(code, message);
    }

    /**
     * @param message 错误信息
     * @return
     */
    public static AppointException errorMessage(String message) {
        return new AppointException(message);
    }
}
