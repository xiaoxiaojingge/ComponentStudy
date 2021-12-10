package org.itjing.lambda;

import java.util.function.Function;

/**
 * @author lijing
 * @date 2021年12月09日 16:46
 * @description
 */
public class LambdaDemo17 {
    public static void main(String[] args) {
        // 使用Lambda表达式创建指定长度的String数组
        Function<Integer, String[]> function = (length) -> new String[length];
        String[] str = function.apply(2);
        System.out.println("str.length = " + str.length);

        // 使用方法引用简化上面的代码
        // String[]::new 是指 size -> new String[size]。
        function = String[]::new;
        str = function.apply(5);
        System.out.println("str.length = " + str.length);
    }
}
