package com.itjing.redis.annotation;

import com.itjing.redis.enu.LimitType;

import java.lang.annotation.*;

/**
 * @author lijing
 * @date 2022年05月26日 9:31
 * @description 限流使用注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {

	/**
	 * 限流key
	 */
	String key() default "rate_limit:";

	/**
	 * 限流时间,单位秒
	 */
	int time() default 60;

	/**
	 * 限流次数
	 */
	int count() default 100;

	/**
	 * 限流类型
	 */
	LimitType limitType() default LimitType.DEFAULT;

}
