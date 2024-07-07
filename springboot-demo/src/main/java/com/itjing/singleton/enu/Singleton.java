package com.itjing.singleton.enu;

/**
 * @author lijing
 * @date 2022年06月01日 20:30
 * @description 枚举单例
 */
public enum Singleton {

	INSTANCE;

	public static Singleton getInstance() {
		return INSTANCE;
	}

	public void doSomething() {
		System.out.println("do something");
	}

	public static void main(String[] args) {
		Singleton.getInstance().doSomething();
		Singleton instance = Singleton.getInstance();
		Singleton instance2 = Singleton.getInstance();
		Singleton instance3 = Singleton.getInstance();
		System.out.println(instance == instance2); // true
		System.out.println(instance2 == instance3); // true
	}

}
