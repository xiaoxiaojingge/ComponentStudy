package com.itjing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.itjing.mapper") //设置mapper接口的扫描包
public class MybatisPlus2021Application {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlus2021Application.class, args);
    }

}
