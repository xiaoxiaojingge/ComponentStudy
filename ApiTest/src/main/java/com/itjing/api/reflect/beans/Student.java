package com.itjing.api.reflect.beans;

import com.itjing.api.reflect.beans.full.Primitive;
import lombok.Data;

import java.util.List;

@Data
public class Student extends People {

	private String sno;

	private List<Course> courses;

	private Primitive[] singles;

	private Integer[] integers;

	private Course mainCourse;

}
