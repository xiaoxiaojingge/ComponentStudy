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
 * @date 2022年05月27日 10:48
 * @description 普通消费者
 */
@Component
@RabbitListener(queues = "normalQueue")
public class NormalConsumer {
    @RabbitHandler
    public void process(Map<String, Object> message, Channel channel, Message mqMsg) throws IOException {
        System.out.println("收到消息，并拒绝重新入队 : " + message.toString());
        channel.basicReject(mqMsg.getMessageProperties().getDeliveryTag(), false);
    }
}
