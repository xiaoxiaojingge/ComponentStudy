package com.itjing.contorller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lijing
 * @date 2021年11月18日 10:21
 * @description
 */
@RestController
public class HelloController {

	@Value("${hello.msg}")
	private String msg;

	@RequestMapping("/hello")
	public String hello() {
		return msg;
	}

}
