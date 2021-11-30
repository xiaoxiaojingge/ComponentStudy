package com.itjing.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lijing
 * @Description 使用Lock锁
 * @create 2020-11-19 19:45
 */
public class sellTicketDemo02 {
    public static void main(String[] args) {

        Ticket2 ticket2 = new Ticket2();

        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket2.sale();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket2.sale();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket2.sale();
            }
        },"C").start();

    }
}


// Lock三部曲
// 1、 new ReentrantLock();
// 2、 lock.lock(); // 加锁
// 3、 finally=>  lock.unlock(); // 解锁
class Ticket2 {
    //属性、方法
    private int number = 50;

    Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();//加锁

        try {
            /*业务代码*/
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了第" + (number--) + "票，剩余" + number + "票。");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//解锁
        }
    }
}