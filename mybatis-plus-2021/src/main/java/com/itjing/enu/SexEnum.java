package com.itjing.enu;

import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * @author lijing
 * @date 2021年12月09日 9:40
 * @description 性别枚举
 */
public enum SexEnum implements IEnum<Integer> {

	MAN(1, "男"),

	WOMAN(2, "女");

	private int value;

	private String desc;

	SexEnum(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	@Override
	public Integer getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}

}
