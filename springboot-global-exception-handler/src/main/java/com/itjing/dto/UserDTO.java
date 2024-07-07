package com.itjing.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * @author lijing
 * @date 2021年12月01日 16:29
 * @description
 */
@Getter
@Setter
public class UserDTO {

	@NotEmpty(message = "用户名不能为空")
	private String name;

	@Min(value = 0, message = "年龄最小不能低于0")
	private int age;

}