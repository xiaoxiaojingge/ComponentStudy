package com.itjing.annotation.jdk.db.provider;

import java.io.Serializable;

/**
 * @author: lijing
 * @Date: 2021年06月02日 16:59 第三步：创建实体类User，并且@Table注解和@Column注解
 * 会被分别标注在User类上和User类中的字段上，将其映射到数据库中的数据表和数据表中的字段上
 */
@Table("t_user")
public class User implements Serializable {

	@Column("id")
	private String id;

	@Column("name")
	private String name;

	public User() {
		super();
	}

	public User(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}

}