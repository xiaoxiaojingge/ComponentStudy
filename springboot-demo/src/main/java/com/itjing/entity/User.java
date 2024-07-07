package com.itjing.entity;

import lombok.*;

/**
 * @author lijing
 * @date 2022年06月01日 15:30
 * @description
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {

	private Integer id;

	private String userName;

	private String telphone;

	private String email;

}
