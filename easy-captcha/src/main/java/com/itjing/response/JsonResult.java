package com.itjing.response;

import com.itjing.enu.HttpStatusCode;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author lijing
 * @date 2022年05月11日 11:36
 * @description
 */
public class JsonResult extends HashMap<String, Object> implements Serializable {

	public static final long serialVersionUID = 1L;

	public JsonResult(int code, String msg, Object data) {
		super(3); // 继承自Map，设置初始容量
		this.put("code", code); // 状态码
		this.put("msg", msg); // 提示消息
		this.put("data", data); // 数据体
	}

	/**
	 * 一般返回code、msg和data这三个即可
	 * @param key
	 * @param value
	 * @return
	 */
	public JsonResult put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	public static JsonResult success() {
		return new JsonResult(HttpStatusCode.OK.getCode(), HttpStatusCode.OK.getZhMessage(), null);
	}

	public static JsonResult success(Object data) {
		return new JsonResult(HttpStatusCode.OK.getCode(), HttpStatusCode.OK.getZhMessage(), data);
	}

	public static JsonResult fail(String msg) {
		return new JsonResult(HttpStatusCode.INTERNAL_SERVER_ERROR.getCode(), msg, null);
	}

	public static JsonResult warn(String msg) {
		return new JsonResult(HttpStatusCode.WARNING.getCode(), msg, null);
	}

}
