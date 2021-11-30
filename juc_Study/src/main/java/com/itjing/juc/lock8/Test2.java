package com.itjing.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author lijing
 * @Description
 *  3、 增加了一个普通方法后！先执行发短信还是Hello？ 普通方法
 *  4、 两个对象，两个同步方法， 发短信还是 打电话？ // 打电话
 * @create 2020-11-20 10:36
 */
public class Test2 {
    public static void main(String[] args) throws InterruptedException {

        // 两个对象，两个调用者，两把锁！
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();

        new Thread(() -> {
            phone1.sendSms();
        }, "A").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            phone2.call();
        }, "B").start();

    }
}

class Phone2 {

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

    public void hello() {
        System.out.println(Thread.currentThread().getName() + "-> hello");
    }
}
