package com.itjing.multithread;

import java.util.concurrent.TimeUnit;

/**
 * 多线程同时操作同一个对象
 * 买火车票的例子
 *
 * 发现问题，多个线程操作同一个资源的情况下，线程不安全，数据紊乱
 */
public class TestThread04 {
    public static void main(String[] args) {
        MyThread04 myThread04 = new MyThread04();
        new Thread(myThread04, "小明").start();
        new Thread(myThread04, "老师").start();
        new Thread(myThread04, "黄牛").start();
    }
}

class MyThread04 implements Runnable {
    //票数
    private int ticketNums = 10;

    @Override
    public void run() {
        while (ticketNums > 0) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-->拿到了第" + ticketNums-- + "票");
        }
    }
}
