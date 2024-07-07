package com.itjing.redis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lijing
 * @date 2022年05月26日 10:16
 * @description 幂等注解 定义此注解的主要目的是把它添加在需要实现幂等的方法上
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoIdempotent {

}