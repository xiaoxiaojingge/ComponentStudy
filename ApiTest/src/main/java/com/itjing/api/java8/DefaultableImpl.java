package com.itjing.api.java8;

public interface DefaultableImpl extends Defaulable {

	@Override
	default String notRequired() {
		return "abc";
	}

}
