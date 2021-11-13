package com.itjing.base.annotation;

import java.lang.annotation.*;

/**
 * @author lijing
 * @date 2021年11月13日 9:46
 * @description 初始化继承 BaseService的 service
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BaseService {
}