package com.itjing.entity.fastjson;

import lombok.*;

import java.util.List;

/**
 * @author: lijing
 * @Date: 2021年05月19日 14:13
 * @Description:
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Teacher {

	private int id;

	private String name;

	private List<Student> studentList;

	public Teacher() {
		System.out.println("调用无参构造方法----Teacher");
	}

}