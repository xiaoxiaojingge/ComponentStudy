package com.itjing.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 用户信息
 */
@TableName(value = "person_info")
public class PersonInfo {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 年龄
     */
    @TableField(value = "user_age")
    private Integer userAge;

    /**
     * 地址
     */
    @TableField(value = "user_address")
    private String userAddress;

    public static final String COL_ID = "id";

    public static final String COL_USER_NAME = "user_name";

    public static final String COL_USER_AGE = "user_age";

    public static final String COL_USER_ADDRESS = "user_address";

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取名称
     *
     * @return user_name - 名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置名称
     *
     * @param userName 名称
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取年龄
     *
     * @return user_age - 年龄
     */
    public Integer getUserAge() {
        return userAge;
    }

    /**
     * 设置年龄
     *
     * @param userAge 年龄
     */
    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    /**
     * 获取地址
     *
     * @return user_address - 地址
     */
    public String getUserAddress() {
        return userAddress;
    }

    /**
     * 设置地址
     *
     * @param userAddress 地址
     */
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userName=").append(userName);
        sb.append(", userAge=").append(userAge);
        sb.append(", userAddress=").append(userAddress);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PersonInfo other = (PersonInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
                && (this.getUserAge() == null ? other.getUserAge() == null : this.getUserAge().equals(other.getUserAge()))
                && (this.getUserAddress() == null ? other.getUserAddress() == null : this.getUserAddress().equals(other.getUserAddress()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getUserAge() == null) ? 0 : getUserAge().hashCode());
        result = prime * result + ((getUserAddress() == null) ? 0 : getUserAddress().hashCode());
        return result;
    }
}