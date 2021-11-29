package com.itjing.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author lijing
 * @date 2021年11月29日 10:44
 * @description
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Room {
    private Integer id;
    private Date time;
    private Student student;
}