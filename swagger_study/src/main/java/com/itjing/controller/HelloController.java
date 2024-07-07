package com.itjing.controller;

import com.itjing.entity.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}

	@RequestMapping("/getUser")
	public User getUser() {
		return new User();
	}

	@ApiOperation("我的接口")
	@PostMapping("/getUserName")
	public String getUserName(@RequestBody @ApiParam("这个名字会被返回") String username) {
		return username;
	}

}
