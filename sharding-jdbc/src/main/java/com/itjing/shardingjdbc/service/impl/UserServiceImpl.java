package com.itjing.shardingjdbc.service.impl;

import com.itjing.shardingjdbc.entity.User;
import com.itjing.shardingjdbc.mapper.UserMapper;
import com.itjing.shardingjdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: lijing
 * @Date: 2021/05/11 14:39
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public List<User> findUsers() {
        return userMapper.findUsers();
    }
}
