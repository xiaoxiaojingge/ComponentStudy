package com.itjing.shardingjdbc.controller;

import com.itjing.shardingjdbc.entity.User;
import com.itjing.shardingjdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/**
 * @author: lijing
 * @Date: 2021/05/11 14:41
 * @Description:
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/save")
    public String insert() {
        User user = new User();
        user.setNickname("lijing" + new Random().nextInt());
        user.setPassword("1234567");
        user.setSex(1);
        user.setBirthday("1997-02-25");
        userService.addUser(user);
        return "success";
    }

    @GetMapping("/listUser")
    public List<User> listUser() {
        return userService.findUsers();
    }
}
