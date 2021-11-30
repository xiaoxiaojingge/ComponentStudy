package com.itjing.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试lock锁
 */
public class TestLock {
    public static void main(String[] args) {
        MyLock lock = new MyLock();
        new Thread(lock, "A").start();
        new Thread(lock, "B").start();
        new Thread(lock, "C").start();
    }
}

class MyLock implements Runnable {

    int ticketNums = 10;

    //可重入锁
    private final Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();//加锁
                if (ticketNums > 0) {
                    System.out.println(Thread.currentThread().getName() + "---->第" + ticketNums-- + "票");
                } else {
                    break;
                }
            } finally {
                lock.unlock();//解锁
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
