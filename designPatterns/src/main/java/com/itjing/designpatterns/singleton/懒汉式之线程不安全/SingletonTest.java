package com.itjing.designpatterns.singleton.懒汉式之线程不安全;

/**
 * @author: lijing
 * @Date: 2021/05/12 17:25
 * @Description: 懒汉式之线程不安全
 */
public class SingletonTest {

	public static void main(String[] args) {
		System.out.println("懒汉式 1 ， 线程不安全~");
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

	public static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}

}
/*
 * 优缺点说明：
 *
 * 1) 起到了 Lazy Loading 的效果，但是只能在单线程下使用。
 *
 * 2) 如果在多线程下，一个线程进入了 if (singleton == null)判断语句块，
 * 还未来得及往下执行，另一个线程也通过了这个判断语句，这时便会产生多个实例。所以在多线程环境下不可使用这种方式
 *
 * 3) 结论：在实际开发中，不要使用这种方式.
 */