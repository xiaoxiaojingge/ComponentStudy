package org.itjing.optional;

import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * @author lijing
 * @date 2021年12月10日 11:57
 * @description
 */
public class OptionalDemo {

    /**
     * 创建Optional实例
     * Optional.of(T value)：不能传null，否则报错
     */
    @Test
    public void testCreateOptional1() {
        Optional<String> optional = Optional.of("凤姐");
        System.out.println("optional.get() = " + optional.get());
    }

    /**
     * 创建Optional实例
     * Optional.empty() 创建一个空的Optional
     */
    @Test
    public void testCreateOptional2() {
        Optional<Object> empty = Optional.empty();
        System.out.println("empty = " + empty);
    }

    /**
     * 创建Optional实例
     * Optional.ofNullable(T value)：
     * 如果value是null，则返回Optional.empty()；
     * 如果value有值，则返回Optional.of(T value)
     */
    @Test
    public void testCreateOptional3() {
        Optional<Object> optional = Optional.ofNullable(null);
        System.out.println("optional = " + optional);
        optional = Optional.ofNullable("你好啊");
        System.out.println("optional = " + optional);
    }

    /**
     * 判断Optional类中是否有值
     */
    @Test
    public void testIsPresent() {
        Optional<Object> empty = Optional.empty();
        if (empty.isPresent()) {
            System.out.println("Optional类中有值");
        } else {
            System.out.println("Optional类中没有值");
        }
    }

    /**
     * 获取Optional类中的值
     */
    @Test
    public void testOptional() {
        Optional<String> optional = Optional.ofNullable(null);
        if (optional.isPresent()) {
            // get()方法，可以用来获取Optional类中的值，如果有值就返回具体值，否则就报错。
            // 一般get()方法配合isPresent()方法使用
            String str = optional.get();
            System.out.println("str = " + str);
        }
    }
}
