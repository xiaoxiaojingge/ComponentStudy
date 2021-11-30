package com.itjing.multithread;

/**
 * 创建线程的方式1：继承Thread类，重写run方法，调用start开启线程
 * 总结：注意，线程开启不一定立即执行，由CPU安排调度
 */
public class TestThread01 {


    public static void main(String[] args) {
        /*自定义线程*/
        MyThread myThread = new MyThread();
        myThread.start();


        /*main线程，主线程执行的代码*/
        for (int i = 0; i < 2000; i++) {
            System.out.println("我在学习多线程" + i);
        }

        /*经过验证，输出的结果是交叉的，说明多线程运行了*/
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        /*run方法线程体*/
        for (int i = 0; i < 2000; i++) {
            System.out.println("我在看代码" + i);
        }
    }
}
