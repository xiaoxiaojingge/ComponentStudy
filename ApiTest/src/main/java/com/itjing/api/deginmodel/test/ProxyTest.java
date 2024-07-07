package com.itjing.api.deginmodel.test;

import com.itjing.api.deginmodel.proxy.static_.Dog;
import com.itjing.api.deginmodel.proxy.static_.DogTimeProxy;
import com.itjing.api.deginmodel.proxy.static_.Moveable;
import com.itjing.api.deginmodel.proxy.static_.TransactionProxy;
import org.junit.Test;

/**
 * 作者: sanri 时间: 2017/08/10 21:37 功能: 代理模式测试
 */
public class ProxyTest {

	@Test
	public void testDog() {
		// 原始信息
		Dog dog = new Dog();
		dog.move();
	}

	@Test
	public void testStaticProxy() {
		// 创建目标类
		Dog dog = new Dog();
		// 创建代理类
		DogTimeProxy dogTimeProxy = new DogTimeProxy(dog);
		dogTimeProxy.move();
	}

	@Test
	public void testProxyStack() {
		// 创建目标类
		Dog dog = new Dog();
		// 创建代理类
		Moveable dogTimeProxy = new DogTimeProxy(dog);
		Moveable transactionProxy = new TransactionProxy(dogTimeProxy);
		transactionProxy.move();
	}

}
