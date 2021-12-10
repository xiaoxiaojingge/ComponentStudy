package org.itjing.lambda;

/**
 * @author lijing
 * @date 2021年12月09日 15:19
 * @description
 */
public class LambdaCondition {
    public static void main(String[] args) {

        test(() -> {
            System.out.println("飞");
        });

        // 局部变量类型是接口，可以使用Lambda
        Flyable flyable = () -> {
            System.out.println("飞");
        };
    }

    /**
     * 方法的参数类型，可以使用Lambda
     *
     * @param flyable
     */
    public static void test(Flyable flyable) {
        flyable.fly();
    }
}
