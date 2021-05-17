package com.itjing.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: lijing
 * @Date: 2021年05月17日 16:50
 * @Description: 实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * id
     */
    private int id;

    /**
     * name
     */
    private String name;
}
