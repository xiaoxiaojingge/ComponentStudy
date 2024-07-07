package org.itjing.inter;

public class DefaultFunction {

	public static void main(String[] args) {
		BB bb = new BB();
		bb.show();

		CC cc = new CC();
		cc.show();
	}

}

interface AA {

	default void show() {
		System.out.println("我是AA接口中的默认方法");
	}

}

// 默认方法的使用方式一：实现类可以直接使用
class BB implements AA {

}

// 默认方法的使用方式二：实现类可以重写默认方法
class CC implements AA {

	@Override
	public void show() {
		System.out.println("我是CC类重写的默认方法");
	}

}