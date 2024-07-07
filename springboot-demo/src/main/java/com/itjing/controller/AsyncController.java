package com.itjing.controller;

import com.itjing.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lijing
 * @date 2022年05月30日 19:59
 * @description
 */
@RestController
@RequestMapping("/async")
public class AsyncController {

	@Autowired
	private AsyncService asyncService;

	@GetMapping("/async")
	public void async() {
		asyncService.executeAsync();
	}

}
