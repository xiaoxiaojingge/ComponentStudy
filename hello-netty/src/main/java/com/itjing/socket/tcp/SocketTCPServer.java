package com.itjing.socket.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: lijing
 * @Date: 2021年09月15日 15:12
 * @Description: Socket服务端
 */
public class SocketTCPServer {
    public static void main(String[] args) throws IOException {
//        startServerByStream();
        startServerByCharacter();
    }

    /**
     * 通过字节流
     *
     * @throws IOException
     */
    public static void startServerByStream() throws IOException {
        // 1．在本机的9999端口监听，等待连接
        // 细节：要求在本机没有其它服务在监听9999，即没有被占用
        // 细节：这个ServerSocket可以通过accept()返回多个Socket[多个客户端连接服务器的并发]
        ServerSocket serverSocket = new ServerSocket(9999);

        System.out.println("服务端，在9999端口监听，等待连接..");

        // 2．当没有客户端连接9999端口时，程序会阻塞，等待连接
        // 如果有客户端连接，则会返回Socket对象，程序继续
        Socket socket = serverSocket.accept();
        System.out.println("服务端 socket =" + socket.getClass());

        // 3．通过socket.getInputstream()读取
        InputStream inputStream = socket.getInputStream();

        // 4. IO读取
        byte[] buff = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buff)) != -1) {
            System.out.println(new String(buff, 0, len));
        }

        // 5. 获取 socket 相关联的输出流
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello,client".getBytes());
        // 设置写入结束的标记
        socket.shutdownOutput();

        // 6.关闭资源
        outputStream.close();
        inputStream.close();
        socket.close();
        serverSocket.close();
    }


    /**
     * 通过字符流
     *
     * @throws IOException
     */
    public static void startServerByCharacter() throws IOException {
        // 1．在本机的9999端口监听，等待连接
        // 细节：要求在本机没有其它服务在监听9999，即没有被占用
        // 细节：这个ServerSocket可以通过accept()返回多个Socket[多个客户端连接服务器的并发]
        ServerSocket serverSocket = new ServerSocket(9999);

        System.out.println("服务端，在9999端口监听，等待连接..");

        // 2．当没有客户端连接9999端口时，程序会阻塞，等待连接
        // 如果有客户端连接，则会返回Socket对象，程序继续
        Socket socket = serverSocket.accept();
        System.out.println("服务端 socket =" + socket.getClass());

        // 3．使用转换流，将字节流转换为字符流
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        // 4. IO读取
        String s = bufferedReader.readLine();
        System.out.println(s); // 输出

        // 5. 获取 socket 相关联的输出流
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("hello,client 字符流");
        bufferedWriter.newLine();
        bufferedWriter.flush();

        // 6.关闭资源
        bufferedWriter.close(); // 关闭外层流
        bufferedReader.close();
        socket.close();
        serverSocket.close();
    }
}
