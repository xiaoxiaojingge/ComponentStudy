package com.itjing.designpatterns.abstractfactory;

/**
 * @author: lijing
 * @Date: 2021年05月27日 16:41
 * @Description: 抽象工厂
 * 与工厂方法模式不同的是，工厂方法模式中的工厂只生产单一的产品，而抽象工厂模式中的工厂生产多个产品
 */
public abstract class AbstractFactory {
    public abstract Color createColor();
    public abstract Shape createShape();
}
