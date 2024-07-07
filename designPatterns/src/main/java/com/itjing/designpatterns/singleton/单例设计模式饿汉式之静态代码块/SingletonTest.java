package com.itjing.designpatterns.singleton.单例设计模式饿汉式之静态代码块;

/**
 * @author: lijing
 * @Date: 2021/05/12 17:08
 * @Description: 单例设计模式饿汉式之静态代码块
 */
public class SingletonTest {

	public static void main(String[] args) {
		Singleton instance1 = Singleton.getInstance();
		Singleton instance2 = Singleton.getInstance();
		System.out.println(instance1 == instance2); // true
		System.out.println("instance1.hashCode=" + instance1.hashCode());
		System.out.println("instance2.hashCode=" + instance2.hashCode());
	}

}

/**
 * 饿汉式（静态常量）应用实例步骤 1、构造器私有化 2、类的内部创建静态私有对象 3、向外暴露一个静态的公共方法，获取实例
 */
class Singleton {

	private Singleton() {
	}

	private static Singleton instance;

	static { // 在静态代码块中，创建单例对象
		instance = new Singleton();
	}

	public static Singleton getInstance() {
		return instance;
	}

}
/*
 * 优缺点说明：
 *
 * 1) 这种方式和上面的方式其实类似，只不过将类实例化的过程放在了静态代码块中，也是在类装载的时候， 就执行静态代码块中的代码，初始化类的实例。优缺点和上面是一样的。
 *
 * 2) 结论：这种单例模式可用，但是可能造成内存浪费
 */