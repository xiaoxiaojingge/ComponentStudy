package com.itjing.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author lijing
 * @date 2022年05月27日 10:51
 * @description 死信队列消费者
 */
@Component
@RabbitListener(queues = "deadQueue")
public class DeadConsumer {
    @RabbitHandler
    public void process(Map<String, Object> message, Channel channel, Message mqMsg) throws IOException {
        System.out.println("死信队列收到消息 : " + message.toString());
        channel.basicAck(mqMsg.getMessageProperties().getDeliveryTag(), false);
    }
}