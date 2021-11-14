package com.itjing;

import com.itjing.base.listener.ContextRefreshedListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MybatisGeneratorApplication implements ApplicationRunner, CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(MybatisGeneratorApplication.class);

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(MybatisGeneratorApplication.class);
        /* 添加监听，这里写的监听类主要是spring初始化完毕后，将对应的mapper注入给通用Service */
        springApplication.addListeners(new ContextRefreshedListener());
        springApplication.run(args);
    }


    // 实现 ApplicationRunner, CommandLineRunner，这两个接口可以在 springboot 启动后，做一些事情，执行 run 方法
    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("ApplicationRunner....run");
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("CommandLineRunner....run");
    }
}
