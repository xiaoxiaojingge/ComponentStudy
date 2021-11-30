package com.itjing.multithread;

/**
 * 创建线程方式2：实现Runnable接口，重写run方法，执行线程需要丢入Runnable接口实现类，调用start方法
 */
public class TestThread03 {
    public static void main(String[] args) {
        MyThread03 myThread03 = new MyThread03();
        new Thread(myThread03).start();

        /*main线程，主线程执行的代码*/
        for (int i = 0; i < 2000; i++) {
            System.out.println("我在学习多线程" + i);
        }
    }
}

class MyThread03 implements Runnable {

    @Override
    public void run() {
        /*run方法线程体*/
        for (int i = 0; i < 2000; i++) {
            System.out.println("我在看代码" + i);
        }
    }
}
