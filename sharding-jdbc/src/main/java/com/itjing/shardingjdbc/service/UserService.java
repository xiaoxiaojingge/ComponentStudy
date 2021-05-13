package com.itjing.shardingjdbc.service;

import com.itjing.shardingjdbc.entity.User;

import java.util.List;

/**
 * @author: lijing
 * @Date: 2021/05/11 14:39
 * @Description:
 */
public interface UserService {
    /**
     * 添加操作
     * @param user
     */
    void addUser(User user);

    /**
     * 查询全部
     * @return
     */
    List<User> findUsers();
}
