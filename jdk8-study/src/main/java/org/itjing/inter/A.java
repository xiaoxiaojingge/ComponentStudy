package org.itjing.inter;

interface A {

	void test1();

	// 接口新增抽象方法，所有实现类都需要去重写这个方法，非常不利于接口的扩展
	void test2();

}

class B implements A {

	@Override
	public void test1() {
		System.out.println("B test1");
	}

	// 接口新增抽象方法，所有实现类都需要去重写这个方法
	@Override
	public void test2() {
		System.out.println("B test2");
	}

}