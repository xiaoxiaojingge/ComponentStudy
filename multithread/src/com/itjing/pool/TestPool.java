package com.itjing.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 */
public class TestPool {
    public static void main(String[] args) {
        //1.创建服务，创建线程池
        //newFixedThreadPool  参数为：线程池大小
        ExecutorService service = Executors.newFixedThreadPool(10);
        service.execute(new myThread());
        service.execute(new myThread());
        service.execute(new myThread());
        service.execute(new myThread());

        //2.关闭
        service.shutdown();
    }
}

class myThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
