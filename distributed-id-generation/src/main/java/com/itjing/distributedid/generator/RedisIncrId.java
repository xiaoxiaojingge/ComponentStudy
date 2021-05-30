package com.itjing.distributedid.generator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 使用redis生成
 */
@Component
@Slf4j
public class RedisIncrId implements IdGenerator {
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public String generateId(int bizType) {
        Long increment = redisTemplate.opsForValue().increment("id:" + bizType, 1);
        return increment+"";
    }
}
