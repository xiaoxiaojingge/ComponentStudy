package org.itjing.lambda;

/**
 * @author lijing
 * @date 2021年12月09日 15:18
 * @description 只有一个抽象方法的接口称为函数式接口。
 */
@FunctionalInterface // 注解用来检测这个接口是不是只有一个抽象方法，即函数式接口
public interface Flyable {

    void fly();
}