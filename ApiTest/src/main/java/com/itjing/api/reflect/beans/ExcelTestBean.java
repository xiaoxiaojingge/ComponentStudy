package com.itjing.api.reflect.beans;


import com.itjing.api.reflect.beans.full.Primitive;

public class ExcelTestBean extends Primitive {
	private String name;
	private String people;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPeople() {
		return people;
	}
	public void setPeople(String people) {
		this.people = people;
	}
}
