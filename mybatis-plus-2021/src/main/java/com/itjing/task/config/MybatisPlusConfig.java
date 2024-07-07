package com.itjing.task.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.itjing.plugins.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lijing
 * @date 2021年12月08日 16:22
 * @description MybatisPlus配置类
 */
@Configuration
public class MybatisPlusConfig {

	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		// 配置分页插件
		PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
		interceptor.addInnerInterceptor(paginationInnerInterceptor);
		// 乐观锁配置
		OptimisticLockerInnerInterceptor optimisticLockerInnerInterceptor = new OptimisticLockerInnerInterceptor();
		interceptor.addInnerInterceptor(optimisticLockerInnerInterceptor);
		// 配置防止全表更新与删除的插件
		BlockAttackInnerInterceptor blockAttackInnerInterceptor = new BlockAttackInnerInterceptor();
		interceptor.addInnerInterceptor(blockAttackInnerInterceptor);
		return interceptor;
	}

	/**
	 * 注册 插件
	 * @return
	 */
	@Bean
	public MyInterceptor myInterceptor() {
		return new MyInterceptor();
	}

}
