package com.itjing.mybatisplus.service.impl;

import com.itjing.mybatisplus.entity.User;
import com.itjing.mybatisplus.mapper.UserMapper;
import com.itjing.mybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jinggege
 * @since 2020-12-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
