package com.itjing.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author lijing
 * @Description
 *  5、增加两个静态的同步方法，只有一个对象，先打印 发短信？打电话？  发短信，因为用的同一把锁，类锁
 *  6、两个对象！增加两个静态的同步方法， 先打印 发短信？打电话？   发短信，因为用的同一把锁，类锁
 * @create 2020-11-20 10:52
 */
public class Test3 {
    public static void main(String[] args) throws InterruptedException {

        // 两个对象的Class类模板只有一个，static，锁的是Class
        Phone3 phone1 = new Phone3();
        Phone3 phone2 = new Phone3();


        new Thread(() -> {
            phone1.sendSms();
        }, "A").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            phone2.call();
        }, "B").start();

    }
}

// Phone3唯一的一个 Class 对象
class Phone3 {

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

    public synchronized static void call() {
        System.out.println(Thread.currentThread().getName() + "-> call");
    }

}