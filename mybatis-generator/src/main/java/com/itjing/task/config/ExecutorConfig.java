package com.itjing.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author lijing
 * @date 2021年11月23日 9:57
 * @description 线程配置，异步操作时使用
 */
@Configuration
public class ExecutorConfig {

    /**
     * 这里Bean的name，不要写成 taskExecutor ，否则会注入别的bean，有同名bean
     * @return
     */
    @Bean(name = "taskExector")
    public ThreadPoolTaskExecutor taskExecutor() {
        // ThreadPoolTaskExecutor 是 spring 对 JUC 下的 ThreadPoolExecutor 的封装
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int i = Runtime.getRuntime().availableProcessors();//获取到服务器的cpu内核
        executor.setCorePoolSize(5);//核心池大小
        executor.setMaxPoolSize(100);//最大线程数
        executor.setQueueCapacity(1000);//队列程度
        executor.setKeepAliveSeconds(1000);//线程空闲时间
        executor.setThreadNamePrefix("jing-executor");//线程前缀名称
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());//配置拒绝策略
        return executor;
    }
}
