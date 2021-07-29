package com.itjing.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * @author: lijing
 * @Date: 2021年07月29日 16:10
 * @Description: 用于处理消息的 handler
 * 由于它的传输数据的载体是frame，这个frame 在netty中，是用于为websocket专门处理文本对象的，
 * frame是消息的载体，此类叫：TextWebSocketFrame
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    // 用于记录和管理所有客户端的channel
    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        // 获取客户端所传输的消息
        String content = msg.text();
        System.out.println("接收到的数据：" + content);

        // 将数据刷新到客户端上
        clients.writeAndFlush(new TextWebSocketFrame(
                "[服务器在：]" + LocalDateTime.now() + "接收到消息，消息内容为：" + content
        ));

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        String chanelId = ctx.channel().id().asShortText();
        System.out.println("客户端被移除：channel id 为：" + chanelId);

        clients.remove(ctx.channel());

    }
}
