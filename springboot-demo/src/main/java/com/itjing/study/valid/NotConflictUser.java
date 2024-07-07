package com.itjing.study.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author lijing
 * @date 2022年06月01日 15:29
 * @description
 */
@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD, PARAMETER, TYPE })
@Constraint(validatedBy = UserValidation.NotConflictUserValidator.class)
public @interface NotConflictUser {

	String message() default "用户名称、邮箱、手机号码与现存用户产生重复";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}