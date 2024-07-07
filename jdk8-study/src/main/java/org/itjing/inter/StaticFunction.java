package org.itjing.inter;

/**
 * @author lijing
 * @date 2021年12月09日 15:45
 * @description
 */
public class StaticFunction {

	public static void main(String[] args) {
		AA.show();
	}

	interface AA {

		static void show() {
			System.out.println("我是接口的静态方法");
		}

	}

}
