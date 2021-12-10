package org.itjing.optional;

import org.itjing.stream.Person;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * @author lijing
 * @date 2021年12月10日 14:13
 * @description
 */
public class OptionalDemo2 {


    @Test
    public void testOrElse() {
        Optional<Object> optional = Optional.empty();

        // orElse：如果Optional中有值，就返回Optional中的值。否则返回orElse方法中参数指定的值
        Object obj = optional.orElse("如花");
        System.out.println("obj = " + obj);
    }

    @Test
    public void testIfPresent() {
        Optional<String> optional = Optional.of("凤姐");
        // ifPresent：如果有值，就调用参数
        optional.ifPresent((s) -> {
            System.out.println("有值：" + s);
        });
        // ifPresentOrElse在JDK9以后才有
        // ifPresentOrElse第一个参数表示如果有值，做什么
        // ifPresentOrElse第二个参数表示如果没有值，做什么
        /*optional.ifPresentOrElse(s -> {
            System.out.println("s = " + s);
        }, () -> {
            System.out.println("没有值");
        });*/
    }

    /**
     * 对比 Optional 和 传统方式
     */
    @Test
    public void testOptional() {
        // 将Person中的用户名转成大写返回
        Person person = null;
        person = new Person("hello world", 18, 99);

        String name = getTraditionUpperName(person);
        System.out.println("name = " + name);

        name = getOptionalUpperName(person);
        System.out.println("name = " + name);

    }

    /**
     * 使用 Optional
     * @param person
     * @return
     */
    public String getOptionalUpperName(Person person) {
        return Optional.ofNullable(person)
                .map(Person::getName)
                .map(String::toUpperCase)
                .orElse(null);
    }

    /**
     * 使用 传统方式
     *
     * @param person
     * @return
     */
    public String getTraditionUpperName(Person person) {
        if (null != person) {
            String name = person.getName();
            if (null != name) {
                return name.toUpperCase();
            }
        }
        return null;
    }

}
