package com.itjing.designpatterns.simplefactory;

/**
 * @author: lijing
 * @Date: 2021年05月27日 16:19
 * @Description: Client 客户端
 */
public class Client {

	public static void main(String[] args) {
		SimpleFactory simpleFactory = new SimpleFactory();

		Car car1 = simpleFactory.createCar(1);
		car1.run();

		Car car2 = simpleFactory.createCar(2);
		car2.stop();
	}

}
