package com.itjing;

import com.itjing.filter.HelloFilter;
import com.itjing.listener.MyListener;
import com.itjing.servlet.HelloServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ServletComponentScan // 2、扫描Servlet
public class SpringbootServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServletApplication.class, args);
	}

	// 3、注册Servlet
	@Bean
	public ServletRegistrationBean<HelloServlet> servlet() {
		// 当配置了uelMappings，那么Servlet自己的配置就不会生效
		ServletRegistrationBean<HelloServlet> bean = new ServletRegistrationBean<>(new HelloServlet(), "/hi");
		// 对servlet进行设置
		bean.setLoadOnStartup(1);
		return bean;
	}
	// 4、启动访问

	// 注册过滤器
	@Bean
	public FilterRegistrationBean<HelloFilter> filter() {
		FilterRegistrationBean<HelloFilter> bean = new FilterRegistrationBean<>(new HelloFilter());
		// 对过滤器进行设置...

		return bean;
	}

	// 注册监听器
	@Bean
	public ServletListenerRegistrationBean<MyListener> listener() {
		ServletListenerRegistrationBean<MyListener> bean = new ServletListenerRegistrationBean<>(new MyListener());
		System.out.println("listening.......");
		return bean;
	}

}
