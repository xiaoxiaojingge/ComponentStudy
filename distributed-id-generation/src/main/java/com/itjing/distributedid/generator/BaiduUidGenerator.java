package com.itjing.distributedid.generator;

import com.baidu.fsg.uid.UidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: lijing
 * @Date: 2021年05月30日 13:59
 * @Description: 使用百度生成器
 */
@Component
public class BaiduUidGenerator implements IdGenerator {

	@Autowired
	UidGenerator uidGenerator;

	@Override
	public String generateId(int bizType) {
		long uid = uidGenerator.getUID();
		return uid + "";
	}

}