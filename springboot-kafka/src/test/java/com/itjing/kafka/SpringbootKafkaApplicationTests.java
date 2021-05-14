package com.itjing.kafka;


import com.itjing.kafka.producer.KafkaProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootKafkaApplicationTests {

    @Autowired
    private KafkaProducer kafkaProducer;// 注入生产者kafkaProducer

    @Test
    public void testKafka() {
        // 生产者发送消息
        kafkaProducer.sendMessage("test", "Hello 你好!");
        kafkaProducer.sendMessage("test", "在吗？");

        // 在这里进行一下线程阻塞，模仿消费者消费消息的过程
        try {
            Thread.sleep(1000 * 3);// 10s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
