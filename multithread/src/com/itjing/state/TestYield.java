package com.itjing.state;

/**
 * 测试礼让线程
 * 礼让不一定成功，看CPU心情
 */
public class TestYield {
    public static void main(String[] args) {
        MyYield yield = new MyYield();
        new Thread(yield,"A").start();
        new Thread(yield,"B").start();
    }
}


class MyYield implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程正在执行！");
        Thread.yield();//礼让
        System.out.println(Thread.currentThread().getName() + "线程停止执行！");
    }
}