package com.itjing.designpatterns.factorymethod;

/**
 * @author: lijing
 * @Date: 2021年05月27日 16:30
 * @Description: 客户端
 */
public class Client {
    public static void main(String[] args) {
        // 由抽象工厂的子类去实例化产品
        AbstractFactory busImpl1Factory = new BusImpl1Factory();
        Bus busImpl1 = busImpl1Factory.create();
        busImpl1.run();

        AbstractFactory busImpl2Factory = new BusImpl2Factory();
        Bus busImpl2 = busImpl2Factory.create();
        busImpl2.stop();
    }
}
