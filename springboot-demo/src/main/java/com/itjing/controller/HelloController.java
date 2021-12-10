package com.itjing.controller;

import com.itjing.entity.Person;
import org.springframework.web.bind.annotation.*;

/**
 * @author lijing
 * @date 2021年12月10日 15:39
 * @description
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/hello")
    public String hello(String name, String sex) {
        return "Hello " + name + "，你的性别是：" + sex + " !";
    }

    @PostMapping("/add")
    public String add(@RequestBody Person person) {
        return person.toString();
    }
}
