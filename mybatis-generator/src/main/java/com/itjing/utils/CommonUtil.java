package com.itjing.utils;

import com.alibaba.fastjson.JSONObject;
import com.itjing.exception.AppointException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lijing
 * @date 2021年11月14日 10:35
 * @description
 */
public class CommonUtil {

	private static Pattern pattern = Pattern.compile("[0-9]*");

	/**
	 * 通过正则判断是否是数字串
	 * @param str
	 * @return
	 */
	public static boolean isNumericByRegex(String str) {
		if (str == null || "".equals(str)) {
			return false;
		}
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 判断是否是数字串
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (str == null || "".equals(str)) {
			return false;
		}
		for (int i = 0; i < str.length(); i++) {
			System.out.println(str.charAt(i));
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isEmptyString(String str) {
		return Objects.isNull(str) || str.isEmpty();
	}

	public static void isError(JSONObject jsonObject) {
		if (Objects.isNull(jsonObject)) {
			throw AppointException.errorMessage("请求体不可为空");
		}
	}

}
