package com.itjing.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lijing
 * @date 2022年05月28日 17:39
 * @description netty服务端处理器
 */
@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

	/**
	 * 客户端连接会触发
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		log.info("Channel active......");
	}

	/**
	 * 客户端发消息会触发
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		log.info("服务器收到消息: {}", msg.toString());
		ctx.write("你也好哦");
		ctx.flush();
	}

	/**
	 * 发生异常触发
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}