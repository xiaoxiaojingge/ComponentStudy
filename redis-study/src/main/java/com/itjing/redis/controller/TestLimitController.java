package com.itjing.redis.controller;

import com.itjing.redis.annotation.RateLimiter;
import com.itjing.redis.enu.LimitType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author lijing
 * @date 2022年05月26日 9:48
 * @description 测试限流
 */
@RestController
@RequestMapping("/limit")
public class TestLimitController {

	/**
	 * 每一个 IP 地址，在 5 秒内只能访问 3 次
	 * @return
	 */
	@GetMapping("/hello")
	@RateLimiter(time = 5, count = 3, limitType = LimitType.IP)
	public String hello() {
		return "hello>>>" + new Date();
	}

}