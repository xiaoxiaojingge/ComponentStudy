package org.itjing.lambda;

import java.util.function.Function;

/**
 * @author lijing
 * @date 2021年12月09日 16:03
 * @description
 */
public class LambdaDemo9 {
    // 使用Lambda表达式将字符串转成数字
    public static void main(String[] args) {
        String str = "10";
        getNumber((s) -> {
            int num = Integer.parseInt(s);
            return num;
        }, str);
    }

    public static void getNumber(Function<String, Integer> function, String str) {
        Integer num = function.apply(str);
        System.out.println("num = " + num);
    }
}
