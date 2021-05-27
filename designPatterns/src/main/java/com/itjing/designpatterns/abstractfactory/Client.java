package com.itjing.designpatterns.abstractfactory;

/**
 * @author: lijing
 * @Date: 2021年05月27日 16:43
 * @Description: 客户端
 */
public class Client {
    public static void main(String[] args) {
        AbstractFactory productFamily1 = new ConcreteFactory1(); // 第一个产品族

        Color colorImpl1 = productFamily1.createColor();
        Shape shapeImpl1 = productFamily1.createShape();
        colorImpl1.fill();
        shapeImpl1.draw();

        AbstractFactory productFamily2 = new ConcreteFactory2(); // 第二个产品族

        Color colorImpl2 = productFamily2.createColor();
        Shape shapeImpl2 = productFamily2.createShape();
        colorImpl2.fill();
        shapeImpl2.draw();
    }
}
