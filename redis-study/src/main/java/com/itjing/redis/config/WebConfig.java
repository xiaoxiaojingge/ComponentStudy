package com.itjing.redis.config;

import com.itjing.redis.interceptor.AutoIdempotentInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author lijing
 * @date 2022年05月26日 10:57
 * @description
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 接口幂等性拦截器
        registry.addInterceptor(autoIdempotentInterceptor());
    }

    @Bean
    public AutoIdempotentInterceptor autoIdempotentInterceptor() {
        return new AutoIdempotentInterceptor();
    }
}
