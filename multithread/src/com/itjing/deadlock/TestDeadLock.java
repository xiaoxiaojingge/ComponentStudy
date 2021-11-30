package com.itjing.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * 死锁：多个线程互相抱着对方需要的资源，然后形成僵持。
 */
public class TestDeadLock {
    public static void main(String[] args) {
        Makeup g1 = new Makeup(0, "灰姑凉");
        Makeup g2 = new Makeup(1, "白雪公主");

        g1.start();
        g2.start();
    }
}

//口红
class Lipastick {

}

//镜子
class Mirror {

}

//
class Makeup extends Thread {

    //需要的资源只有一份，用static来保证只有一份
    static Lipastick lipastick = new Lipastick();
    static Mirror mirror = new Mirror();

    int choice;//选择
    String girlName;//使用化妆品的人

    public Makeup(int choice, String girlName) {
        this.choice = choice;
        this.girlName = girlName;
    }

    @Override
    public void run() {
        //化妆
        try {
            makeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //化妆，互相持有对方的锁，就是需要拿到对方的资源
    private void makeup() throws InterruptedException {
        if (choice == 0) {
            synchronized (lipastick) {//获得口红的锁
                System.out.println(this.girlName + "获得口红的锁");
                TimeUnit.SECONDS.sleep(1);
                synchronized (mirror) {//一秒钟后想要获得镜子
                    System.out.println(this.girlName + "获得镜子的锁");
                }
            }
        } else {
            synchronized (mirror) {//获得镜子的锁
                System.out.println(this.girlName + "获得镜子的锁");
                TimeUnit.SECONDS.sleep(2);
                synchronized (lipastick) {//一秒钟后想要获得口红
                    System.out.println(this.girlName + "获得口红的锁");
                }
            }
        }
    }
}
