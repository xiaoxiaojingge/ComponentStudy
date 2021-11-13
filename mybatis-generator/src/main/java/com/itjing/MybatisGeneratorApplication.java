package com.itjing;

import com.itjing.base.listener.ContextRefreshedListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.itjing.mapper")
public class MybatisGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(MybatisGeneratorApplication.class);
        /* 添加监听，这里写的监听类主要是spring初始化完毕后，将对应的mapper注入给通用Service */
        springApplication.addListeners(new ContextRefreshedListener());
        springApplication.run(args);
    }

}
