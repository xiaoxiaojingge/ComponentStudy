package com.itjing.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author: lijing
 * @Date: 2021年10月15日 11:23
 * @Description: netty服务端
 */
public class NettyServer {
    public static void main(String[] args) {
        // 创建一组线程池组
        // 主线程池：用于接受客户端的请求连接，不做任何处理
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 从线程池，主线程池，会把任务交给它，让其做任务
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 创建服务器启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 设置主从线程组
            serverBootstrap.group(bossGroup, workerGroup)
                    // 设置NIO双向通道
                    .channel(NioServerSocketChannel.class)
                    // 添加子处理器，用于处理从线程池的任务,自定义
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast("encoder", new ObjectEncoder());
                            pipeline.addLast(" decoder", new
                                    io.netty.handler.codec.serialization.ObjectDecoder(Integer.MAX_VALUE,
                                    ClassResolvers.cacheDisabled(null)));

                            // 重点，其他的都是复用的
                            // 这是真正的I0的业务代码，把他封装成一个个的个Hand1e类就行了
                            // 把他当成 SpringMVC的Controller
                            pipeline.addLast(new NettyServerHandler());

                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 启动服务，并且设置端口号，同时启动方式为同步
            ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();
            System.out.println("服务端启动成功，端口号为:" + 8888);

            // 监听关闭的channel，设置为同步方式
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
