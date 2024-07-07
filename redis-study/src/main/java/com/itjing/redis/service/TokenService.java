package com.itjing.redis.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lijing
 * @date 2022年05月26日 10:17
 * @description
 */
public interface TokenService {

	/**
	 * 创建token
	 * @return
	 */
	String createToken();

	/**
	 * 检验token
	 * @param request
	 * @return
	 */
	boolean checkToken(HttpServletRequest request) throws Exception;

}