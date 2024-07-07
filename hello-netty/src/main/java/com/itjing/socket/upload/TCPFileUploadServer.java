package com.itjing.socket.upload;

import com.itjing.utils.StreamUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: lijing
 * @Date: 2021年09月15日 16:33
 * @Description: 文件上传服务端
 */
public class TCPFileUploadServer {

	public static void main(String[] args) throws IOException {
		// 1、监听8888端口
		ServerSocket serverSocket = new ServerSocket(8888);
		System.out.println("服务器在8888端口监听...");

		// 2、等待连接
		Socket socket = serverSocket.accept();

		// 3、读取客户端发送的数据
		BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
		byte[] bytes = StreamUtils.streamToByteArray(bis);

		// 4、将得到的 bytes 数组写入到指定路径，就得到一个文件了
		String destFilePath = "E:\\workspace_idea\\ComponentStudy\\hello-netty\\src\\main\\resources\\images\\002.png";
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFilePath));
		bos.write(bytes);
		bos.close();

		// 向客户端回复 收到图片
		// 通过 Socket 获取输出流
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		bw.write("客户端在吗，我收到图片了！");
		// bw.newLine();
		bw.flush();
		socket.shutdownOutput(); // 设置写入结束标记

		// 关闭其它资源
		bw.close();
		bis.close();
		socket.close();
		serverSocket.close();
	}

}
