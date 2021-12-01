package com.itjing.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author lijing
 * @date 2021年12月01日 16:27
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private int age;
}
