package com.itjing.shardingjdbc.entity;

import lombok.Data;

/**
 * @author: lijing
 * @Date: 2021/05/11 14:35
 * @Description:
 */
@Data
public class User {
    // 主键
    private Integer id;
    // 昵称
    private String nickname;
    // 密码
    private String password;
    // 性别
    private Integer sex;
    // 生日
    private String birthday;
}
