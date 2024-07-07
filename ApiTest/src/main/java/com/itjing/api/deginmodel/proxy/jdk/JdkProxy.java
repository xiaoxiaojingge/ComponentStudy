package com.itjing.api.deginmodel.proxy.jdk;

import com.itjing.api.deginmodel.proxy.testcglib.User;
import com.itjing.api.deginmodel.proxy.testcglib.UserOperator;

import java.lang.reflect.Proxy;

public class JdkProxy {

	public static void main(String[] args) {
		UserOperator proxyInstance = (UserOperator) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
				new Class[] { UserOperator.class }, new ProxyHandler());
		User sanri = proxyInstance.queryByName("sanri");
		System.out.println(sanri);
	}

}
