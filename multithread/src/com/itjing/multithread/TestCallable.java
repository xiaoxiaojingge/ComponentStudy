package com.itjing.multithread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

/**
 * 线程创建方式3：实现callable接口
 * 好处：
 * 1.可以定义返回值
 * 2.可以抛出异常
 */
public class TestCallable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable myCallable1 = new MyCallable("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606026269639&di=be049ee4c5f2a6a3a22a6746630c2567&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201811%2F16%2F20181116195909_klmfb.thumb.700_0.jpg", "4.jpg");
        MyCallable myCallable2 = new MyCallable("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606026269638&di=b57def68f3cf8da1ed310b36e9fb1549&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ffront%2F511%2Fw1024h1087%2F20190416%2FsUnA-hvsckth5610974.jpg", "5.jpg");
        MyCallable myCallable3 = new MyCallable("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606026269638&di=4b3599e3ad4d3c11fb56d87feb09b2ad&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201811%2F16%2F20181116084944_yuwqb.jpg", "6.jpg");

        //创建执行服务
        // 阿里巴巴开发手册建议用ThreadPoolExecutor 创建
        ExecutorService service = Executors.newFixedThreadPool(3);

        //提交执行
        Future<Boolean> r1 = service.submit(myCallable1);
        Future<Boolean> r2 = service.submit(myCallable2);
        Future<Boolean> r3 = service.submit(myCallable3);

        //获取结果
        Boolean rs1 = r1.get();
        Boolean rs2 = r2.get();
        Boolean rs3 = r3.get();

        //关闭服务
        service.shutdownNow();
    }
}

class MyCallable implements Callable<Boolean> {
    private String url; //网络图片地址
    private String name; //保存的文件名


    public MyCallable(String url, String name) {
        this.url = url;
        this.name = name;
    }

    /*下载图片的线程的执行体*/
    @Override
    public Boolean call() {
        WebDownLoader2 webDownLoader = new WebDownLoader2();
        webDownLoader.download(this.url, this.name);
        System.out.println("下载了的文件名为" + name);
        return true;
    }
}

//下载器
class WebDownLoader2 {
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

