package com.itjing.api.apachecommons;

import java.lang.reflect.Method;

/**
 *
 * 作者:sanri</br>
 * 时间:2016-9-28上午11:46:21</br>
 * 功能:扩展自 apache.BeanUtils 增加获取方法的方法 <br/>
 * 问题:在加载所有的类文件时有点慢主要是为了找到 stopClass 看有没有好的办法优化下<br/>
 */
public class Bean {

	static class GetMethodFilter implements MethodFilter {

		@Override
		public boolean accept(Method method, String methodName) {
			return methodName.indexOf("get") != -1;
		}

	}

	static class SetMethodFilter implements MethodFilter {

		@Override
		public boolean accept(Method method, String methodName) {
			return methodName.indexOf("set") != -1;
		}

	}

	public final static GetMethodFilter GET_METHOD_FILTER = new GetMethodFilter();

	public final static SetMethodFilter SET_METHOD_FILTER = new SetMethodFilter();

	public interface MethodFilter {

		boolean accept(Method method, String methodName);

	}

}
