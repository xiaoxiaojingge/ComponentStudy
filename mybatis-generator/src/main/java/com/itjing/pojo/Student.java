package com.itjing.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author lijing
 * @date 2021年11月29日 10:45
 * @description
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Student {
    private Integer id;
    private String name;
    private Integer gender;
    private Date birthday;
    private String home;

}