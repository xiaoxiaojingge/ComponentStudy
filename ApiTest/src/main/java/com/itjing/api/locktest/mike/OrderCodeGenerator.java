package com.itjing.api.locktest.mike;

public class OrderCodeGenerator {

	static int orderNo = 1;

	public int getOrderCode() {
		return orderNo++;
	}

}
