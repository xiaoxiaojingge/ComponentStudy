package com.itjing.service;

import com.itjing.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lijing
 * @date 2022年06月01日 15:32
 * @description
 */
@Service
public class UserRepository {

    public boolean existsByUserNameOrEmailOrTelphone(String userName, String email, String telphone) {
        return true;
    }


    public List<User> findByUserNameOrEmailOrTelphone(String userName, String email, String telphone) {
        return new ArrayList<>();
    }

    public User save(User user) {
        user.setId(100);
        return user;
    }
}
