package org.itjing.lambda;

/**
 * @author lijing
 * @date 2021年12月09日 14:29
 * @description
 */
public class LambdaDemo3 {

	/**
	 * Lambda表达式的标准格式： (参数列表)->{} (参数列表)：参数列表 {}：方法体 ->：没有实际含义，起到连接的作用
	 */
	public static void main(String[] args) {

		/*
		 * goSwimming(new Swimmable() {
		 *
		 * @Override public void swimming() { System.out.println("我是匿名内部类的游泳"); } });
		 */

		goSwimming(() -> {
			System.out.println("我是Lambda的游泳");
		});
	}

	/**
	 * 无参数无返回值的Lambda
	 * @param swimmable
	 */
	public static void goSwimming(Swimmable swimmable) {
		swimmable.swimming();
	}

}
