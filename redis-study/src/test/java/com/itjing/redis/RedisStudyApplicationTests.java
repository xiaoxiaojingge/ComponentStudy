package com.itjing.redis;

import com.alibaba.fastjson.JSONObject;
import com.itjing.redis.entity.User;
import com.itjing.redis.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class RedisStudyApplicationTests {

	@Test
	void contextLoads() {
		User user = new User(1, "lijing");
		RedisUtil.StringOps.set("user", JSONObject.toJSONString(user));
	}

}
