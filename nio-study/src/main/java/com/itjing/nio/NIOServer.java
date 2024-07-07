package com.itjing.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author: lijing
 * @Date: 2021年10月15日 10:18
 * @Description: nio服务端
 */
public class NIOServer {

	public static void main(String[] args) throws IOException {
		// 第一步
		// Service端的Channel，监听端口的
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		// 设置为非阻塞
		serverChannel.configureBlocking(false);
		// nio的api规定这样赋值端口
		serverChannel.bind(new InetSocketAddress(8000));
		// 显示Channel是否已经启动成功
		System.out.println("服务端启动成功，监听端口为8000，等待客户端连接...." + serverChannel.getLocalAddress());

		// 第二步
		// 声明selector选择器
		Selector selector = Selector.open();
		// 这句话是指，把selector注册到Channel上面
		// 每个客户端来了之后，就把客户端注册到Selector选择器上，默认状态是ACCEPT
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);

		// 第三步
		// 创建buffer缓冲区，声明大小是1024，底层使用数组来实现的
		ByteBuffer buffer = ByteBuffer.allocate(1024);

		// 第四步
		// 轮询，服务端不断轮询，等待客户端的连接
		// 如果有客户端轮询上来就取出对应的Channel，没有就一直轮询
		while (true) {
			int select = selector.select();
			if (select == 0) {
				continue;
			}
			// 有可能有很多，使用Set保存Channel
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = selectionKeys.iterator();
			// 迭代处理
			while (iterator.hasNext()) {
				// 使用SelectionKey来获取连接了客户端和服务端的Channel
				SelectionKey key = iterator.next();
				// 判断SelectionKey中的Channel状态如何，如果是OP_ACCEPT就进入
				if (key.isAcceptable()) {
					// 从判断SelectionKey中取出Channel
					ServerSocketChannel channel = (ServerSocketChannel) key.channel();
					// 拿到对应客户端的Channel
					SocketChannel clientChannel = channel.accept();
					// 把客户端的Channel打印出来
					System.out.println("客户端通道信息打印：" + clientChannel.getRemoteAddress());
					// 设置客户端的Channel设置为非阻塞
					clientChannel.configureBlocking(false);
					clientChannel.register(selector, SelectionKey.OP_READ);
				}
				// 到此轮询到的时候，发现状态是read，开始进行数据交互

				if (key.isReadable()) {
					// 以buffer作为数据桥梁
					SocketChannel channel = (SocketChannel) key.channel();

					try {
						// 数据要想读要先写，必须先读取到buffer里面进行操作
						channel.read(buffer);
						// 进行读取
						String request = new String(buffer.array()).trim();
						buffer.clear();
						// 进行打印buffer中的数据
						System.out.println(String.format("客户端发来的消息： %s : %s", channel.getRemoteAddress(), request));

						// TODO 这里也可以向客户端发送消息

					}
					catch (IOException e) {
						try {
							channel.close();
						}
						catch (IOException ioException) {
							ioException.printStackTrace();
						}
						System.out.println("客户端断开了连接~~");
					}
				}

				iterator.remove();
			}
		}
	}

}
