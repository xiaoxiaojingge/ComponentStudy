package com.itjing.controller;

import com.itjing.listener.MyListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author lijing
 * @date 2021年11月20日 14:08
 * @description
 */
@RestController
public class TestController {

    @GetMapping("/login")
    public String login(HttpSession session) {
        // 设置session，从而触发监听器的sessionCreated
        session.setAttribute("name", "aa");
        return "login";
    }

    @GetMapping("/online")
    public String online() {
        return "当前在线人数" + MyListener.online + "人";
    }
}
