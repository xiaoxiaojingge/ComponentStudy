package com.itjing;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author: lijing
 * @Date: 2021年07月29日 13:35
 * @Description: 实现客户端发送请求，服务器给予响应
 */
public class HelloNettyServer {

	public static void main(String[] args) {
		// 创建一组线程池组
		// 主线程池：用于接受客户端的请求连接，不做任何处理
		EventLoopGroup group1 = new NioEventLoopGroup();
		// 从线程池，主线程池，会把任务交给它，让其做任务
		EventLoopGroup group2 = new NioEventLoopGroup();
		try {
			// 创建服务器启动类
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			// 设置主从线程组
			serverBootstrap.group(group1, group2)
				// 设置NIO双向通道
				.channel(NioServerSocketChannel.class)
				// 添加子处理器，用于处理从线程池的任务,自定义
				.childHandler(new HelloNettyServerInitializer());
			// 启动服务，并且设置端口号，同时启动方式为同步
			ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();
			// 监听关闭的channel，设置为同步方式
			channelFuture.channel().closeFuture().sync();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally {
			group1.shutdownGracefully();
			group2.shutdownGracefully();
		}
	}

}
