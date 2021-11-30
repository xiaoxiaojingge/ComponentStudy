package com.itjing.juc.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lijing
 * @Description
 * @create 2020-11-19 21:50
 */
public class B {
    public static void main(String[] args) {
        Data2 data2 = new Data2();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data2.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();


        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data2.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data2.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data2.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}

// 判断等待，业务，通知
class Data2 {// 数字 资源类
    private int number = 0;
    Lock lock = new ReentrantLock();

    Condition condition = lock.newCondition();


    public void increment() throws InterruptedException {
        lock.lock();

        try {
            while (number != 0) { //0
                /*等待*/
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "->" + number);
            // 通知其他线程，我+1完毕了
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (number != 1) { //1
                /*等待*/
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "->" + number);
            // 通知其他线程，我-1完毕了
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

