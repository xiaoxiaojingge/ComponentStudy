package com.itjing.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author lijing
 * @Description 8锁，就是关于锁的8个问题
 *  1、标准情况下，两个线程先打印 发短信还是 打电话？ 1/发短信  2/打电话
 *  2、sendSms延迟4秒，两个线程先打印 发短信还是 打电话？ 1/发短信  2/打电话
 * @create 2020-11-20 10:06
 */
public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();

        /*锁的存在*/
        new Thread(() -> {
            phone.sendSms();
        }, "A").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            phone.call();
        }, "B").start();

        /*
        输出的顺序
        A-> sendSms
        B-> call
        */
    }
}

class Phone {

    /*synchronized 锁的对象是方法的调用者！*/
    /*两个方法用的是同一个锁，谁先拿到谁执行*/
    public synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "-> sendSms");
    }

    public synchronized void call() {
        System.out.println(Thread.currentThread().getName() + "-> call");
    }
}
