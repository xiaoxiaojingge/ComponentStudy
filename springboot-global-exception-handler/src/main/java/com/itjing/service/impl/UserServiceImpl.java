package com.itjing.service.impl;

import com.itjing.controller.UserController;
import com.itjing.dto.UserDTO;
import com.itjing.entity.User;
import com.itjing.exception.BizException;
import com.itjing.exception.UserErrorEnum;
import com.itjing.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

/**
 * @author lijing
 * @date 2021年12月01日 16:26
 * @description
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Override
    public User add(UserDTO userDTO) {
        String userId = UUID.randomUUID().toString();
        return new User(userId, userDTO.getName(), userDTO.getAge());
    }

    @Override
    public User getById(String id) {
        // 模拟业务异常
        if (Objects.equals(id, "000")) {
            throw new BizException(UserErrorEnum.USER_NOT_FOUND);
        }
        return new User(id, "Mr.nobody", 18);
    }

    @Override
    public void marry(String age) {
        // 当age不是数字字符串时，抛出异常
        Integer integerAge = Integer.valueOf(age);
        logger.info(String.valueOf(integerAge));
    }
}
