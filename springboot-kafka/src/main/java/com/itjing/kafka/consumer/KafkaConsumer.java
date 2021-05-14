package com.itjing.kafka.consumer;



import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author: lijing
 * @Date: 2021年05月14日 17:45
 * @Description:
 */
@Component
class KafkaConsumer {

    /**
     * 消费者订阅的主题为test
     * 就是通过 @KafkaListener(topics = {"test"}) 注解实现的
     *
     * @param record 接收的消息被封装成 ConsumerRecord 对象
     */
    @KafkaListener(topics = {"test"})
    public void handleMessage(ConsumerRecord record) {
        System.out.println(record.value());
    }
}
