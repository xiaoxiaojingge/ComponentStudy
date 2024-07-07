package com.itjing.utils;

import java.util.UUID;

/**
 * @author lijing
 * @date 2021年11月14日 15:09
 * @description
 */
public class UUIDUtil {

	private UUIDUtil() {

	}

	public static String get() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

}
