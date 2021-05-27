package com.itjing.designpatterns.simplefactory;

/**
 * @author: lijing
 * @Date: 2021年05月27日 16:16
 * @Description: ConcreteProduct 具体产品角色
 */
public class CarImpl1 implements Car {
    @Override
    public void run() {
        System.out.println("CarImpl1 is running");
    }

    @Override
    public void stop() {
        System.out.println("CarImpl1 stop running");
    }
}
