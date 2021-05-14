package com.itjing.oss.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: lijing
 * @Date: 2021年05月14日 13:58
 * @Description: 阿里云 OSS 基本配置
 */
@Configuration
// 指定配置文件中自定义属性前缀
@ConfigurationProperties(prefix = "aliyun")
@Data// lombok
@Accessors(chain = true)// 开启链式调用
public class AliyunOSSConfig {
    private String endPoint;// 地域节点
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;// OSS的Bucket名称
    private String urlPrefix;// Bucket 域名
    private String fileHost;// 目标文件夹

    // 将OSS 客户端交给Spring容器托管
    @Bean
    public OSS OSSClient() {
        return new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
    }
}