package com.itjing.task.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.itjing.mapper") // 这里写扫描mapper的话，启动类上就不用写了
@EnableTransactionManagement
@Configuration // 配置类
public class MyBatisPlusConfig {

	// 注册乐观锁插件    
	@Bean
	public OptimisticLockerInterceptor optimisticLockerInterceptor() {
		return new OptimisticLockerInterceptor();
	}

	// 分页插件
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}

	// 逻辑删除组件！
	@Bean
	public ISqlInjector sqlInjector() {
		return new LogicSqlInjector();
	}

	// SQL执行效率插件    
	@Bean
	@Profile({ "dev", "test" }) // 设置 dev test 环境开启，保证我们的效率
	public PerformanceInterceptor performanceInterceptor() {
		PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
		performanceInterceptor.setMaxTime(100); // 设置sql执行的最大时间，如果超过了则不执行    
		performanceInterceptor.setFormat(true); // 是否格式化代码  
		return performanceInterceptor;
	}

}
