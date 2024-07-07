package com.itjing.designpatterns.abstractfactory;

/**
 * @author: lijing
 * @Date: 2021年05月27日 16:43
 * @Description:
 */
public class ConcreteFactory2 extends AbstractFactory {

	@Override
	public Color createColor() {
		return new Blue();
	}

	@Override
	public Shape createShape() {
		return new Circle();
	}

}
