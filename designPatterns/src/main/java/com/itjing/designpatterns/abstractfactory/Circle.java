package com.itjing.designpatterns.abstractfactory;

/**
 * @author: lijing
 * @Date: 2021年05月27日 16:38
 * @Description:
 */
public class Circle implements Shape {

	@Override
	public void draw() {
		System.out.println("The shape is circle");
	}

}
