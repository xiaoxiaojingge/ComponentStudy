package com.itjing.designpatterns.abstractfactory;

/**
 * @author: lijing
 * @Date: 2021年05月27日 16:42
 * @Description: 具体工厂类（生成多个产品，产品族）
 */
public class ConcreteFactory1 extends AbstractFactory {
    @Override
    public Color createColor() {
        return new Red();
    }

    @Override
    public Shape createShape() {
        return new Square();
    }
}
