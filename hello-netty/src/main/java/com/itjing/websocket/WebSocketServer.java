package com.itjing.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author: lijing
 * @Date: 2021年07月29日 15:54
 * @Description: 编写WebSocketServer实现实时通信
 */
public class WebSocketServer {
    private static class SingletionWSServer {
        static final WebSocketServer instance = new WebSocketServer();
    }

    public static WebSocketServer getInstance() {
        return SingletionWSServer.instance;
    }

    private EventLoopGroup mainGroup;
    private EventLoopGroup subGroup;
    private ServerBootstrap server;
    private ChannelFuture future;

    public WebSocketServer() {
        // 创建主从线程池
        mainGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        // 创建服务器启动类
        server = new ServerBootstrap();
        // 设置主从线程池
        server.group(mainGroup, subGroup)
                // 设置NIO双向通道
                .channel(NioServerSocketChannel.class)
                // 添加子处理器，用于处理从线程池的任务,自定义
                .childHandler(new WSServerInitialzer());
    }

    public void start() {
        this.future = server.bind("127.0.0.1", 8888);
        if (future.isSuccess()) {
            System.out.println("启动 Netty 成功");
        }
    }

    public static void main(String[] args) {
        getInstance().start();
    }
}
