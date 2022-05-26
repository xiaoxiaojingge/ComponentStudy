package com.itjing.redis.controller;

import com.itjing.redis.annotation.AutoIdempotent;
import com.itjing.redis.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lijing
 * @date 2022年05月26日 11:01
 * @description
 */
@RestController
@RequestMapping("/autoIdempotent")
public class AutoIdempotentController {

    @Autowired
    private TokenService tokenService;

    @GetMapping("/token")
    public String token() {
        return tokenService.createToken();
    }

    @AutoIdempotent
    @PostMapping("/dealSomething")
    public String dealSomething() {
        return "success";
    }


}
