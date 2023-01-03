package com.itjing.component.annotation;

import java.lang.annotation.*;

/**
 * @Description: 重复提交注解
 * @Author: lijing
 * @CreateTime: 2023-01-03 15:24
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
//@Documented
public @interface NoRepeatSubmit {

    /**
     * 间隔时间，单位秒
     */
    int interval() default 3 * 1000;

    /**
     * 提示消息
     */
    String message() default "不允许重复提交，请稍候再试";

}
