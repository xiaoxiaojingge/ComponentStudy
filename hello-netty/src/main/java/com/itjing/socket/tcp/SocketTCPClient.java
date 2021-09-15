package com.itjing.socket.tcp;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author: lijing
 * @Date: 2021年09月15日 15:16
 * @Description: Socket客户端（  相关命令：netstat -anb  ）
 */
public class SocketTCPClient {
    public static void main(String[] args) throws IOException {
//        startClientByStream();
        startClientByCharacter();
    }


    /**
     * 使用字节流
     * @throws IOException
     */
    public static void startClientByStream() throws IOException {
        // 1．连接服务端(ip，端口)，得到 Socket
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端 socket =" + socket.getClass());

        // 2. 连上后，拿到Socket，通过 socket.getgetOutputStream() 获得与其相关的输出流对象
        OutputStream outputStream = socket.getOutputStream();

        // 3．通过输出流，写入数据到数据通道
        outputStream.write("hello,server".getBytes());
        // 设置写入结束的标记
        socket.shutdownOutput();

        // 4. 获取和socket关联的输入流、读取数据(字节)，并显示
        InputStream inputStream = socket.getInputStream();
        byte[] buff = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buff)) != -1) {
            System.out.print(new String(buff, 0, len));
        }

        // 5. 关闭流对象和socket，必须关闭
        inputStream.close();
        outputStream.close();
        socket.close();

        System.out.println("客户端退出！！！");
    }

    /**
     * 使用字符流
     * @throws IOException
     */
    public static void startClientByCharacter() throws IOException {
        // 1．连接服务端(ip，端口)，得到 Socket
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端 socket =" + socket.getClass());

        // 2. 连上后，拿到Socket，通过 socket.getgetOutputStream() 获得与其相关的输出流对象
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

        // 3．写入数据到数据通道
        bufferedWriter.write("hello,server 字符流");
        // 插入一个换行符，表示写入内容结束，此时要求对方必须使用 readLine() 读取！！！！！
        bufferedWriter.newLine();
        // 如果使用字符流，需要手动刷新，否则数据不会写入数据通道
        bufferedWriter.flush();

        // 4. 获取和socket关联的输入流
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        System.out.println(s);

        // 5. 关闭流对象和socket，必须关闭
        bufferedReader.close(); // 关闭外层流
        bufferedWriter.close();
        socket.close();

        System.out.println("客户端退出！！！");
    }
}
