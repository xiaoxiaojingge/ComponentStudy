package com.itjing.api.deginmodel.proxy.testcglib;

public interface UserOperator {

	User queryByName(String name);

	void insertUser(User user);

}
