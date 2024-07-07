package com.itjing.annotation.jdk.db.provider;

import java.lang.annotation.*;

/**
 * @author: lijing
 * @Date: 2021年06月02日 16:46 第一步：我们创建一个 com.itjing.annotation.jdk.db.provider 包，
 * 在这个包创建一个@Table注解，@Table注解标注在Java类上，表示当前类会被映射到数据库中的哪张数据表上，
 */
@Inherited // 当@Inherited注解加在某个类A上时，假如类B继承了A，则B也会带上该注解。
/**
 * @Target 说明了Annotation所修饰的对象范围 1.CONSTRUCTOR:用于描述构造器 2.FIELD:用于描述域
 * 3.LOCAL_VARIABLE:用于描述局部变量 4.METHOD:用于描述方法 5.PACKAGE:用于描述包 6.PARAMETER:用于描述参数
 * 7.TYPE:用于描述类、接口(包括注解类型) 或enum声明
 */
@Target({ ElementType.TYPE })
/**
 * 注解按生命周期来划分可分为3类：
 *
 * 1、RetentionPolicy.SOURCE：注解只保留在源文件，当Java文件编译成class文件的时候，注解被遗弃；
 * 2、RetentionPolicy.CLASS：注解被保留到class文件，但jvm加载class文件时候被遗弃，这是默认的生命周期；
 * 3、RetentionPolicy.RUNTIME：注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在；
 * 这3个生命周期分别对应于：Java源文件(.java文件) ---> .class文件 ---> 内存中的字节码。
 */
@Retention(RetentionPolicy.RUNTIME)
/**
 * @Documented 用于描述其它类型的注解应该被作为被标注的程序成员的公共API，因此可以被例如 javadoc 此类的工具文档化。
 */
@Documented
public @interface Table {

	String value() default "";

}
