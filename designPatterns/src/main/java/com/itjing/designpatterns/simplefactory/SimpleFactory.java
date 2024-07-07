package com.itjing.designpatterns.simplefactory;

/**
 * @author: lijing
 * @Date: 2021年05月27日 16:18
 * @Description: Factory 工厂角色
 */
public class SimpleFactory {

	public Car createCar(int type) {
		if (type == 1) {
			return new CarImpl1();
		}
		else if (type == 2) {
			return new CarImpl2();
		}
		return null;
	}

}
