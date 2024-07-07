package com.itjing.springboot.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

/**
 * @author lijing
 * @date 2024-06-28
 */

@Data
@JacksonXmlRootElement(localName = "school")
public class SchoolBO {

	// 可以指定XML标签的名字。若不指定，则为字段名
	@JacksonXmlProperty(localName = "SchoolName")
	private String schoolName;

	// isAttribute 设为true时，该字段做为根标签的属性
	@JacksonXmlProperty(isAttribute = true)
	private Integer schoolId;

	private String schoolEmail;

	private String schoolAddress;

	// 指定外层的XML标签名
	@JacksonXmlElementWrapper(localName = "studentList")
	// 指定单个元素的XML标签名
	@JacksonXmlProperty(localName = "student")
	private List<StudentBO> studentList;

}
