package org.itjing.lambda;

import java.util.function.Consumer;

/**
 * @author lijing
 * @date 2021年12月09日 16:01
 * @description
 */
public class LambdaDemo8 {
    public static void main(String[] args) {
        // 使用Lambda表达式先将一个字符串转成小写，再转成大写
        String str = "hello world";
        test((s) -> {
            System.out.println(s.toLowerCase());
        }, (s) -> {
            System.out.println(s.toUpperCase());
        }, str);
    }

    public static void test(Consumer<String> c1, Consumer<String> c2, String str) {
        c1.andThen(c2).accept(str);
    }
}
