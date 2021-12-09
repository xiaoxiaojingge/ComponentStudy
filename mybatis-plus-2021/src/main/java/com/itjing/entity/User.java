package com.itjing.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.itjing.enu.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author lijing
 * @date 2021年12月08日 15:19
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_user")
@Accessors(chain = true)
@ToString
public class User extends Model<User> {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField("user_name") // 解决字段名不一致
    private String username;

    /**
     * 密码
     */
    @TableField(select = false) // 该字段不加入到查询中
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 电子邮件
     */
    private String email;

    @TableField(exist = false)
    private String address; // 该字段在数据库表中不存在

    // 性别为枚举
    private SexEnum sex;

    @Version
    private Integer version;

    /**
     * 为createTime添加自动填充功能，在新增数据的时候有效
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 为updateTime添加自动填充功能，在更新数据的时候有效
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    @TableLogic
    private Integer deleted;
}
