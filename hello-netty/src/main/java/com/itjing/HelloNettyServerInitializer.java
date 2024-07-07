package com.itjing;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author: lijing
 * @Date: 2021年07月29日 13:55
 * @Description: 初始化器，channel注册之后，会执行里边的相应的初始化方法
 */
public class HelloNettyServerInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel socketChannel) throws Exception {
		// 获取管道（pipeline）
		ChannelPipeline pipeline = socketChannel.pipeline();
		/**
		 * 通过管道添加 handler
		 */
		// websocket 基于http协议，所需要的 http 编解码器
		// HttpServerCodec 是由netty自己提供的助手类，可以理解为拦截器
		// 当请求到服务器，我们需要解码，响应到客户端做编码
		pipeline.addLast("HttpServerCodec", new HttpServerCodec());
		// 添加自定义助手类，给客户端浏览器渲染 hello netty
		pipeline.addLast("CustomHandler", new CustomHandler());

	}

}
