package com.itjing.netty;

import com.itjing.netty.server.NettyServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SpringbootNettyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootNettyApplication.class, args);
		// 开启Netty服务
		NettyServer nettyServer = new NettyServer();
		nettyServer.start();
		log.info("======服务已经启动========");
	}

}
