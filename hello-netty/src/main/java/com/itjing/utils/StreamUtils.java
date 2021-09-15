package com.itjing.utils;

import java.io.*;

/**
 * @author: lijing
 * @Date: 2021年09月15日 16:41
 * @Description: 关于流的读写
 */
public class StreamUtils {

    /**
     * 将输入流转换成 byte[],即可以把文件的内容读入到 byte[]
     *
     * @param is
     * @return
     * @throws IOException
     */
    public static byte[] streamToByteArray(InputStream is) throws IOException {
        // 创建输出流对象
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        // 字节数组
        byte[] b = new byte[1024];
        int len;
        // 循环读取
        while ((len = is.read(b)) != -1) {
            bos.write(b, 0, len);
        }
        byte[] array = bos.toByteArray();
        bos.close();
        return array;
    }

    /**
     * InputSteam转String
     *
     * @param is
     * @return
     */
    public static String inputStreamToString(InputStream is) {
        try {
            StringBuilder sb = new StringBuilder();
            for (int ch; (ch = is.read()) != -1; ) {
                sb.append((char) ch);
            }
            String str = sb.toString();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * InputSteam转String
     *
     * @param is
     * @return
     * @throws Exception
     */
    public static String inputStreamToString2(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line + "\r\n");
        }
        return builder.toString();
    }
}
