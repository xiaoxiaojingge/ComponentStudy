package com.itjing.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author: lijing
 * @Date: 2021年10月15日 11:31
 * @Description: 需要做的IO操作，重点是继承 ChannelInboundHandlerAdapter类就好了
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		System.out.println(String.format("客户端信息： %s", channel.remoteAddress()));
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		Channel channel = ctx.channel();
		String request = (String) msg;
		System.out.println(String.format("客户端发送的消息 %s : %s", channel.remoteAddress(), request));

		// TODO 定义向客户端发送的数据内容
	}

}
