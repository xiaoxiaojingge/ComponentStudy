package com.itjing.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author lijing
 * @date 2021年12月06日 9:43
 * @description
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@XStreamAlias("Teacher")
@XmlRootElement(name = "Teacher")
public class Teacher {

	@XStreamAlias("id")
	@XStreamAsAttribute
	private Integer id;

	@XStreamAlias("name")
	private String name;

	@XStreamAlias("gender")
	private String gender;

	@XStreamAlias("age")
	private Integer age;

	@XStreamAlias("homeAddress")
	private String home;

	@XStreamAlias("major")
	private String major;

}
