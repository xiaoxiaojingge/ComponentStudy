package com.itjing.threadlocal.entity;

/**
 * @author lijing
 * @date 2022年05月31日 15:09
 * @description
 */
public class User {

	private Long id;

	private String name;

	public User() {
	}

	public User(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", name='" + name + '\'' + '}';
	}

}
