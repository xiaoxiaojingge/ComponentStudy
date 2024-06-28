package com.itjing.springboot.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lijing
 * @date 2024-06-28
 */
@Data
public class StudentBO {

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}