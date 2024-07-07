package com.itjing.netty.controller;

import com.itjing.netty.client.NettyClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author lijing
 * @date 2022年05月28日 18:07
 * @description
 */
@RestController
@Slf4j
public class NettyController {

	@GetMapping("/helloNetty")
	public Map<String, Object> helloNetty(String msg) {
		return NettyClientUtil.helloNetty(msg);
	}

}
