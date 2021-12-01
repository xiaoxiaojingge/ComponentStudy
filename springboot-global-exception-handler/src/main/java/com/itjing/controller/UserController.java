package com.itjing.controller;

import com.itjing.dto.UserDTO;
import com.itjing.entity.User;
import com.itjing.response.GeneralResult;
import com.itjing.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author lijing
 * @date 2021年12月01日 16:22
 * @description
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public GeneralResult<User> add(@RequestBody @Valid UserDTO userDTO) {
        User user = userService.add(userDTO);
        return GeneralResult.genSuccessResult(user);
    }

    @GetMapping("/find/{userId}")
    public GeneralResult<User> find(@PathVariable String userId) {
        User user = userService.getById(userId);
        return GeneralResult.genSuccessResult(user);
    }

    @GetMapping("/marry/{age}")
    public GeneralResult<User> marry(@PathVariable String age) {
        userService.marry(age);
        return GeneralResult.genSuccessResult();
    }
}
