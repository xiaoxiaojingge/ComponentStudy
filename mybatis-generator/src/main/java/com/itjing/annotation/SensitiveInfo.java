package com.itjing.annotation;

import com.itjing.utils.SensitiveUtils;

import java.lang.annotation.*;

/**
 * @author lijing
 * @date 2021年11月17日 11:04
 * @description 自定义脱敏注解
 */
@Target(value = { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SensitiveInfo {

	SensitiveUtils.sensitiveType Type();

}