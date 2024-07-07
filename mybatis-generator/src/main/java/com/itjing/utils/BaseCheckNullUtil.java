package com.itjing.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lijing
 * @date 2021年11月14日 10:33
 * @description 非空判断工具类
 */
public class BaseCheckNullUtil {

	/**
	 * 检查字符串是否为空 包括空字符串,去空格
	 * @param str
	 * @return true:空 false：非空
	 */
	public static boolean isNullTrim(String str) {
		return (null == str || "".trim().equals(str));
	}

	/**
	 * 检查字符串是否为空 包括空字符串
	 * @param str
	 * @return true:空 false：非空
	 */
	public static boolean isNull(String str) {
		return (null == str || "".equals(str.trim()));
	}

	/**
	 * 检查list是否为空 包括list的size为0
	 * @param lst
	 * @return true:空 false：非空
	 */
	public static boolean isNull(List<?> lst) {
		return null == lst || 0 == lst.size();
	}

	/**
	 * 检查set是否为空 包括set的size为0
	 * @param set
	 * @return true:空 false：非空
	 */
	public static boolean isNull(Set<?> set) {
		return null == set || 0 == set.size();
	}

	/**
	 * 检查数组是否为空
	 * @param objs
	 * @return true:空 false：非空
	 */
	public static boolean isNull(Object[] objs) {
		if (null == objs) {
			return true;
		}
		else {
			for (int i = 0; i < objs.length; i++) {
				if (objs[i] != null && !"".equals(objs[i].toString().trim())) {
					return false;
				}
			}
			return true;
		}
	}

	/**
	 * 检查map是否为空 包括map的size为0
	 * @param map
	 * @return
	 */
	public static boolean isNull(Map<?, ?> map) {
		return null == map || 0 == map.size();
	}

	/**
	 * 检查object是否为空
	 * @param obj
	 * @return true 空
	 */
	public static boolean isNull(Object obj) {
		return null == obj;
	}

	public static boolean isNullOrEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		if (obj instanceof CharSequence) {
			return ((CharSequence) obj).length() == 0;
		}
		if (obj instanceof Collection) {
			return ((Collection) obj).isEmpty();
		}
		if (obj instanceof Map) {
			return ((Map) obj).isEmpty();
		}
		if (obj instanceof Object[]) {
			Object[] object = (Object[]) obj;
			if (object.length == 0) {
				return true;
			}
			boolean empty = true;
			for (int i = 0; i < object.length; i++) {
				if (!isNullOrEmpty(object[i])) {
					empty = false;
					break;
				}
			}
			return empty;
		}
		return false;
	}

}
