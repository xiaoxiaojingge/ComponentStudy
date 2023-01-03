package com.itjing.oss.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: lijing
 * @Date: 2021年05月14日 14:28
 * @Description: Swagger 配置类
 */
@Configuration
@EnableSwagger2 // 开启swagger2
public class Swagger2Config {

    @Bean
    public Docket webApiConfig() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }

    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("SpringBoot整合OSS-API文档")
                .description("阿里云OSS-文件上传下载测试")
                .version("1.0")
                .contact(new Contact("lijing", "https://www.xiaojingge.com", "2427259171@qq.com"))
                .build();
    }
}