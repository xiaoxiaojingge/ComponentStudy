package com.itjing.springsecurity.dao;

import com.itjing.springsecurity.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    User selectByName(String username);

}
