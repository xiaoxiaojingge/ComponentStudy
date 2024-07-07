package com.itjing.socket.upload;

import com.itjing.utils.StreamUtils;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author: lijing
 * @Date: 2021年09月15日 16:33
 * @Description: 文件上传客户端
 */
public class TCPFileUploadClient {

	public static void main(String[] args) throws IOException {
		// 连接服务端
		Socket socket = new Socket(InetAddress.getLocalHost(), 8888);
		// 创建读取磁盘文件的输入流
		String filePath = "E:\\workspace_idea\\ComponentStudy\\hello-netty\\src\\main\\resources\\images\\001.png";
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));

		// bytes 就是 文件 对应的字节数组
		byte[] bytes = StreamUtils.streamToByteArray(bis);

		// 通过 Socket 获取输出流，将bytes数据发送给服务端
		BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
		bos.write(bytes); // 将文件对应字节数组写入到数据通道中

		bis.close();

		socket.shutdownOutput(); // 输出结束标记

		// 接收从服务端回复的消息
		InputStream is = socket.getInputStream();
		String s = StreamUtils.inputStreamToString2(is);
		System.out.println(s);

		// 关闭相关的流
		is.close();
		bos.close();
		socket.close();

	}

}
