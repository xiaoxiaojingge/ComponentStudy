package com.itjing.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author lijing
 * @date 2021年11月20日 13:43
 * @description 1、编写Filter 当请求路径，匹配了，就会执行过滤方法 过滤所有请求 /*
 */
@WebFilter(filterName = "helloFilter", urlPatterns = "/*")
public class HelloFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("init");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		System.out.println("filter");

		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
		System.out.println("destroy");
	}

}
