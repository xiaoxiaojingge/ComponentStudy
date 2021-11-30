package com.itjing.multithread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 练习使用Thread，实现多线程同步下载图片
 */
public class TestThread02 {

    public static void main(String[] args) {
        MyThread02 myThread1 = new MyThread02("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606026269639&di=be049ee4c5f2a6a3a22a6746630c2567&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201811%2F16%2F20181116195909_klmfb.thumb.700_0.jpg", "1.jpg");
        MyThread02 myThread2 = new MyThread02("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606026269638&di=b57def68f3cf8da1ed310b36e9fb1549&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ffront%2F511%2Fw1024h1087%2F20190416%2FsUnA-hvsckth5610974.jpg", "2.jpg");
        MyThread02 myThread3 = new MyThread02("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606026269638&di=4b3599e3ad4d3c11fb56d87feb09b2ad&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201811%2F16%2F20181116084944_yuwqb.jpg", "3.jpg");

        /*每次下载的顺序都不一定相同*/
        myThread1.start();
        myThread2.start();
        myThread3.start();
    }
}

class MyThread02 extends Thread {
    private String url; //网络图片地址
    private String name; //保存的文件名


    public MyThread02(String url, String name) {
        this.url = url;
        this.name = name;
    }

    /*下载图片的线程的执行体*/
    @Override
    public void run() {
        WebDownLoader webDownLoader = new WebDownLoader();
        webDownLoader.download(this.url, this.name);
        System.out.println("下载了的文件名为" + name);
    }
}

//下载器
class WebDownLoader {
    //下载方法
    public void download(String url, String name) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常，download方法出现问题！");
        }
    }
}
