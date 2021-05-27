package com.itjing.designpatterns.factorymethod;

/**
 * @author: lijing
 * @Date: 2021年05月27日 16:30
 * @Description: 具体工厂 ConcreteFactory
 */
public class BusImpl1Factory extends AbstractFactory {
    @Override
    public Bus create() {
        return new BusImpl1();
    }
}
