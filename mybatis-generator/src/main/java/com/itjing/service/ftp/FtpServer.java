package com.itjing.service.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lijing
 * @date 2022年06月25日 10:00
 * @description
 */
public class FtpServer {

    // ftp对象
    private static FTPClient ftp;

    // 需要连接到的ftp端的ip
    private String ip;

    // 连接端口，默认21
    private int port = 21;

    // 要连接到的ftp端的名字
    private String name;

    // 要连接到的ftp端的对应得密码
    private String pwd;

    public FtpServer(String ip, String name, String pwd) {
        this(ip, 21, name, pwd);
    }

    public FtpServer(String ip, int port, String name, String pwd) {
        this.ip = ip;
        this.port = port;
        this.name = name;
        this.pwd = pwd;
        this.init();
    }

    private void init() {
        ftp = new FTPClient();
        //验证登录
        try {
            ftp.connect(ip, port);
            ftp.login(name, pwd);
            ftp.setCharset(Charset.forName("UTF-8"));
            ftp.setControlEncoding("UTF-8");
        } catch (IOException e) {
            throw new RuntimeException("连接失败");
        }
    }

    /**
     * 上传文件
     *
     * @param SourcePath 本地文件
     * @param TargetPath FTP服务器文件
     */
    public void UploadFile(String SourcePath, String TargetPath) {
        try {

            OutputStream os = ftp.storeFileStream(TargetPath);
            FileInputStream fis = new FileInputStream(SourcePath);
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = fis.read(b)) != -1) {
                os.write(b, 0, len);
            }
            os.close();
            fis.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("UploadFile 上传文件失败" + e.getMessage());
        }
    }

    /**
     * 重命名
     *
     * @param OldName 旧名称
     * @param NewName 新名称
     */
    public void Rename(String OldName, String NewName) {

    }

    /**
     * 下载文件
     *
     * @param SourcePath 本地文件
     * @param TargetPath FTP文件
     */
    public void DownloadFile(String SourcePath, String TargetPath) {
        try {
            InputStream is = ftp.retrieveFileStream(TargetPath);
            FileOutputStream fos = new FileOutputStream(new File(SourcePath));
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = is.read(b)) != -1) {
                fos.write(b, 0, len);
            }
            fos.close();
            is.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("DownloadFile 下载文件失败" + e.getMessage());
        }
    }

    public InputStream getInputStream(String TargetPath) {
        try {
            InputStream is = ftp.retrieveFileStream(TargetPath);
            return is;
        } catch (IOException e) {
            throw new RuntimeException("获取文件输入流失败：" + e.getMessage());
        }
    }

    /**
     * 删除文件
     *
     * @param DelPath 删除文件路径
     */
    public void DeleteFile(String DelPath) {
        try {
            ftp.deleteFile(DelPath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("删除文件异常%s", e.getMessage()));
        }
    }

    /**
     * 获取根目录全部文件目录文件名称
     */
    public void FileList() {
        FileList("/");
    }

    /**
     * 获取选中文件节点下所以文件目录名称
     *
     * @param DirName
     */
    public void FileList(String DirName) {
        FTPFile[] files = new FTPFile[0];
        try {
            files = ftp.listFiles(DirName);
            //打印出ftp里面，“Windows”文件夹里面的文件名字
            for (FTPFile file : files) {
                System.out.println(file.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("FileList错误%s", e.getMessage()));
        }
    }

    /**
     * 判断文件是否存在
     *
     * @param FilePath
     * @return
     */
    public boolean FileExists(String FilePath) {
        try {
            FTPFile[] files = ftp.listFiles(FilePath);
            if (files != null && files.length > 0) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("FileExists 文件是否存在异常 %s", e.getMessage()));
        }
    }

    public static void main(String args[]) {
        String ip = "192.168.56.111"; // 临时域名
        String username = "test"; // 用户名
        String password = "test"; // 密码
        int port = 21;
        FtpServer ftpServer = new FtpServer(ip, port, username, password);
//        ftpServer.DownloadFile("E:\\workspace_idea\\ComponentStudy\\mybatis-generator\\src\\main\\java\\com\\itjing\\service\\ftp\\2.del","1.del");
        InputStream inputStream = ftpServer.getInputStream("1.del");
        List<String> strs = readDel(inputStream);
        System.out.println(strs);
    }

    private static List<String> readDel(InputStream inputStream) {
        List<String> allString = new ArrayList<>();
        try (DataInputStream dis = new DataInputStream(inputStream);
             BufferedReader br = new BufferedReader(new InputStreamReader(dis, "UTF-8"))) {
            String line = "";
            String everyLine = "";
            // 读取到的内容给line变量
            while ((line = br.readLine()) != null) {
                everyLine = line;
                allString.add(everyLine);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allString;
    }

}
