package com.itjing.webmagic;

import com.itjing.webmagic.task.ZhihuTask;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.itjing.webmagic.mapper")
public class WebMagicDemoApplication implements CommandLineRunner {

    @Autowired
    private ZhihuTask zhihuTask;

    public static void main(String[] args) {
        SpringApplication.run(WebMagicDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 爬取知乎数据
        zhihuTask.crawl();
    }
}
