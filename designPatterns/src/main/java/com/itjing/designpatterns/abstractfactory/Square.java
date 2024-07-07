package com.itjing.designpatterns.abstractfactory;

/**
 * @author: lijing
 * @Date: 2021年05月27日 16:39
 * @Description:
 */
public class Square implements Shape {

	@Override
	public void draw() {
		System.out.println("The shape is square");
	}

}
