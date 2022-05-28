package com.itjing.annotation;

import java.lang.annotation.*;

/**
 * @author lijing
 * @date 2022年05月26日 16:51
 * @description 自定义注解，标记支持 form 表单、form-data、json
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InterfaceType {
}
