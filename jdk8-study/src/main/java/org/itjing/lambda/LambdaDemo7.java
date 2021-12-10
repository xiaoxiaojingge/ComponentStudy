package org.itjing.lambda;

import java.util.function.Consumer;

/**
 * @author lijing
 * @date 2021年12月09日 15:59
 * @description
 */
public class LambdaDemo7 {
    public static void main(String[] args) {
        String str = "hello world";
        test((s) -> {
            String lowerCase = s.toLowerCase();
            System.out.println(lowerCase);

            String upperCase = s.toUpperCase();
            System.out.println(upperCase);
        }, str);
    }

    public static void test(Consumer<String> consumer, String str) {
        consumer.accept(str);
    }
}
