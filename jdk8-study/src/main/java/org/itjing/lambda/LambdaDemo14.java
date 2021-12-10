package org.itjing.lambda;

import java.util.function.Supplier;

/**
 * @author lijing
 * @date 2021年12月09日 16:30
 * @description
 */
public class LambdaDemo14 {
    public static void main(String[] args) {
        // 使用Lambda表达式获取当前的毫秒值
        Supplier<Long> supplier = () -> System.currentTimeMillis();
        System.out.println("supplier = " + supplier.get());
        // 使用方法引用简化上面的代码
        supplier = System::currentTimeMillis;
        System.out.println("supplier = " + supplier.get());
    }
}
