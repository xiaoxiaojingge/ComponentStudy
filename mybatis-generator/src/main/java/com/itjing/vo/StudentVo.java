package com.itjing.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author lijing
 * @date 2021年11月29日 10:46
 * @description
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class StudentVo {

	private Integer studentId;

	private String name;

	private String gender;

	// 在Mapper之中进行了转换
	private String birthday;

	private String homeLocation;

}