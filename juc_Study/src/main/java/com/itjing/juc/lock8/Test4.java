package com.itjing.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author lijing
 * @Description
 * 7、1个静态的同步方法，1个普通的同步方法 ，一个对象，先打印 发短信？打电话？   打电话
 * 8、1个静态的同步方法，1个普通的同步方法 ，两个对象，先打印 发短信？打电话？
 * @create 2020-11-20 11:05
 */
public class Test4 {
    public static void main(String[] args) throws InterruptedException {

        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();

        new Thread(() -> {
            phone1.sendSms();
        }, "A").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            phone2.call();
        }, "B").start();

    }
}

class Phone4 {

    // synchronized 锁的对象是方法的调用者！
    // static 静态方法
    // 类一加载就有了！锁的是Class
    public synchronized static void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "-> sendSms");
    }

    //普通的同步方法，使用的是对象锁
    public synchronized void call() {
        System.out.println(Thread.currentThread().getName() + "-> call");
    }

}