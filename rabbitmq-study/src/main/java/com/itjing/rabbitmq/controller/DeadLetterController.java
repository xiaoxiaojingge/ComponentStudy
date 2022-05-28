package com.itjing.rabbitmq.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author lijing
 * @date 2022年05月27日 10:24
 * @description
 */
@RestController
@Slf4j
@RequestMapping("/rabbit")
public class DeadLetterController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 正常消息队列，队列最大长度5
     */
    @GetMapping("/normalQueue")
    public String normalQueue() {

        Map<String, Object> map = new HashMap<>();
        map.put("messageId", String.valueOf(UUID.randomUUID()));
        map.put("data", System.currentTimeMillis() + ", 正常队列消息，最大长度 5");

        rabbitTemplate.convertAndSend("normalExchange", "normalRouting", map, new CorrelationData());
        return JSONObject.toJSONString(map);
    }

    /**
     * 消息 TTL, time to live
     */
    @GetMapping("/ttlToDead")
    public String ttlToDead() {

        Map<String, Object> map = new HashMap<>();
        map.put("messageId", String.valueOf(UUID.randomUUID()));
        map.put("data", System.currentTimeMillis() + ", ttl队列消息");

        rabbitTemplate.convertAndSend("normalExchange", "ttlRouting", map, new CorrelationData());
        return JSONObject.toJSONString(map);
    }

}
