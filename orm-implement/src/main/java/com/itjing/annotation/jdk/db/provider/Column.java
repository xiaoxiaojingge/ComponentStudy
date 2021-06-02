package com.itjing.annotation.jdk.db.provider;

import java.lang.annotation.*;

/**
 * @author: lijing
 * @Date: 2021年06月02日 16:57
 * @Description: 第二步：创建一个@Column注解，@Column注解标注在类中的字段上，表示当前类中的字段映射到数据表中的哪个字段上
 */
@Inherited
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {
    String value() default "";
}