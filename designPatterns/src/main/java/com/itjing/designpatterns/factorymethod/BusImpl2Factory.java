package com.itjing.designpatterns.factorymethod;

/**
 * @author: lijing
 * @Date: 2021年05月27日 16:30
 * @Description:
 */
public class BusImpl2Factory extends AbstractFactory {
    @Override
    public Bus create() {
        return new BusImpl2();
    }
}
