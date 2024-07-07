package org.itjing.lambda;

/**
 * @author lijing
 * @date 2021年12月09日 14:23
 * @description
 */
public class LambdaDemo2 {

	public static void main(String[] args) {
		// Lambda体现的是函数式编程思想，只需要将要执行的代码放到函数中（函数就是类中的方法）
		// Lambda就是一个匿名函数，我们只需要将要执行的代码放到Lambda表达式中即可
		// Lambda表达式的好处：可以简化匿名内部类，让代码更加精简。
		new Thread(() -> {
			System.out.println("线程执行啦(*^▽^*)");
		}).start();
	}

}
