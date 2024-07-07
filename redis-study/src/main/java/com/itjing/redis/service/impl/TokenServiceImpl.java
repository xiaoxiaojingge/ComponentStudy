package com.itjing.redis.service.impl;

import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.itjing.redis.common.Constant;
import com.itjing.redis.common.ResponseCode;
import com.itjing.redis.exception.ServiceException;
import com.itjing.redis.service.TokenService;
import com.itjing.redis.utils.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lijing
 * @date 2022年05月26日 10:18
 * @description
 */
@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private RedisService redisService;

	/**
	 * 创建token
	 * @return
	 */
	@Override
	public String createToken() {

		String str = RandomUtil.randomString(16);
		StrBuilder token = new StrBuilder();
		try {
			token.append(Constant.Redis.TOKEN_PREFIX).append(str);
			redisService.setEx(token.toString(), token.toString(), 10000L);
			boolean notEmpty = StrUtil.isNotEmpty(token.toString());
			if (notEmpty) {
				return token.toString();
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 检验token
	 * @param request
	 * @return
	 */
	@Override
	public boolean checkToken(HttpServletRequest request) throws Exception {

		String token = request.getHeader(Constant.TOKEN_NAME);
		if (StrUtil.isBlank(token)) {// header中不存在token
			token = request.getParameter(Constant.TOKEN_NAME);
			if (StrUtil.isBlank(token)) {// parameter中也不存在token
				throw new ServiceException(ResponseCode.ILLEGAL_ARGUMENT.getMsg(),
						ResponseCode.ILLEGAL_ARGUMENT.getCode());
			}
		}

		if (!redisService.exists(token)) {
			throw new ServiceException(ResponseCode.REPETITIVE_OPERATION.getMsg(),
					ResponseCode.REPETITIVE_OPERATION.getCode());
		}

		boolean remove = redisService.remove(token);
		// 注意删除后一定要校验是否删除
		// 不能单纯的直接删除token而不校验是否删除成功,
		// 会出现并发安全性问题, 因为, 有可能多个线程同时走到此行,
		// 此时token还未被删除, 所以继续往下执行, 如果不校验删除结果而直接放行,
		// 那么还是会出现重复提交问题, 即使实际上只有一次真正的删除操作
		if (!remove) {
			throw new ServiceException(ResponseCode.REPETITIVE_OPERATION.getMsg(),
					ResponseCode.REPETITIVE_OPERATION.getCode());
		}

		return true;
	}

}
