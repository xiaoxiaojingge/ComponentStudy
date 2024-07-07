package com.itjing.distributedid.config;

import com.baidu.fsg.uid.UidGenerator;
import com.baidu.fsg.uid.impl.CachedUidGenerator;
import com.baidu.fsg.uid.worker.DisposableWorkerIdAssigner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 百度UID生成器配置类
 */
@Configuration
public class BaiduBeansConfig {

	@Bean
	public DisposableWorkerIdAssigner disposableWorkerIdAssigner() {
		return new DisposableWorkerIdAssigner();
	}

	@Bean("cachedUidGenerator")
	public UidGenerator uidGenerator() {
		CachedUidGenerator uidGenerator = new CachedUidGenerator();
		uidGenerator.setWorkerIdAssigner(disposableWorkerIdAssigner());
		return uidGenerator;
	}

}
