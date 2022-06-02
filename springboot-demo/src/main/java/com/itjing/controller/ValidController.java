package com.itjing.controller;

import com.itjing.entity.User;
import com.itjing.service.UserRepository;
import com.itjing.study.valid.NotConflictUser;
import com.itjing.study.valid.UniqueUser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author lijing
 * @date 2022年06月01日 15:38
 * @description
 */
@RestController
@RequestMapping("/valid/user")
@Slf4j
@Validated
public class ValidController {

    @Autowired
    private UserRepository userRepository;

    /**
     * 创建用户
     * @param user
     * @return
     */
    @PostMapping
    public User createUser(@UniqueUser @Valid User user) {
        User savedUser = userRepository.save(user);
        log.info("save user id is {}", savedUser.getId());
        return savedUser;
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @SneakyThrows
    @PutMapping
    public User updateUser(@NotConflictUser @Valid @RequestBody User user) {
        User editUser = userRepository.save(user);
        log.info("update user is {}", editUser);
        return editUser;
    }
}