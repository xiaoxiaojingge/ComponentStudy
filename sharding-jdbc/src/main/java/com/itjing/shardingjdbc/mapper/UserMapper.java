package com.itjing.shardingjdbc.mapper;

import com.itjing.shardingjdbc.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: lijing
 * @Date: 2021/05/11 14:36
 * @Description: 用户操作
 */
@Mapper
@Repository
public interface UserMapper {

    /**
     * 添加操作
     * @param user
     */
    @Insert("insert into jing_user(nickname,password,sex,birthday) values(#{nickname},#{password},#{sex},#{birthday})")
    void addUser(User user);

    /**
     * 查询全部
     * @return
     */
    @Select("select id,nickname,password,sex,birthday from jing_user")
    List<User> findUsers();
}
