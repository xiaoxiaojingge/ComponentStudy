package com.itjing.entity.fastjson;

import lombok.*;

/**
 * @author: lijing
 * @Date: 2021年05月19日 13:56
 * @Description:
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Student {
    private int id;
    private String name;
    private int age;

    public Student() {
        System.out.println("调用无参构造方法----Student");
    }
}