package com.itjing.designpatterns.factorymethod;

/**
 * @author: lijing
 * @Date: 2021年05月27日 16:28
 * @Description: 具体产品 ConcreteProduct
 */
public class BusImpl1 implements Bus {

	@Override
	public void run() {
		System.out.println("BusImpl1 is running");
	}

	@Override
	public void stop() {
		System.out.println("BusImpl1 stop running");
	}

}
