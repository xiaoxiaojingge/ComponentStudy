package com.itjing.designpatterns.singleton.懒汉式线程安全之同步方法;

/**
 * @author: lijing
 * @Date: 2021/05/12 17:25
 * @Description: 懒汉式线程安全之同步方法
 */
public class SingletonTest {

	public static void main(String[] args) {
		System.out.println("懒汉式 2 ， 线程安全~");
		Singleton instance = Singleton.getInstance();
		Singleton instance2 = Singleton.getInstance();
		System.out.println(instance == instance2); // true
		System.out.println("instance.hashCode=" + instance.hashCode());
		System.out.println("instance2.hashCode=" + instance2.hashCode());
	}

}

class Singleton {

	private static Singleton instance;

	private Singleton() {
	}

	// 加同步关键字
	public static synchronized Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}

}
/*
 * 优缺点说明：
 *
 * 1) 解决了线程安全问题
 *
 * 2) 效率太低了，每个线程在想获得类的实例时候，执行 getInstance()方法都要进行同步。 而其实这个方法只执行一次实例化代码就够了，后面的想获得该类实例，直接
 * return 就行了。方法进行同步效率太低
 *
 * 3) 结论：在实际开发中，不推荐使用这种方式
 */