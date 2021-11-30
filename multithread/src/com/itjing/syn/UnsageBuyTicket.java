package com.itjing.syn;

import java.util.concurrent.TimeUnit;

/**
 * 不安全的买票
 */
public class UnsageBuyTicket {
    public static void main(String[] args) {
        BuyTicket station = new BuyTicket();

        new Thread(station, "苦逼的我").start();
        new Thread(station, "牛逼的你们").start();
        new Thread(station, "可恶的黄牛党").start();
    }
}

class BuyTicket implements Runnable {

    //票
    private int ticketNums = 100;
    boolean flag = true; //外部停止方式

    @Override
    public void run() {
        //买票
        while (flag) {
            buy();
        }
    }

    //买票方法
    private synchronized void buy() {
        //判断是否有票
        if (ticketNums <= 0) {
            flag = false;
            return;
        }
        //模拟延时
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //买票
        System.out.println(Thread.currentThread().getName() + "拿到了第" + ticketNums-- + "票");
    }
}