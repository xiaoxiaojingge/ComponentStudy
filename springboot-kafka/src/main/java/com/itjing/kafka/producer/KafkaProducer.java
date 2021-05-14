package com.itjing.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author: lijing
 * @Date: 2021年05月14日 17:44
 * @Description:
 */
@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;// 注入kafkaTemplate

    /**
     * 发送消息
     *
     * @param topic   消息主题
     * @param content 消息内容
     */
    public void sendMessage(String topic, String content) {
        kafkaTemplate.send(topic, content);
    }
}