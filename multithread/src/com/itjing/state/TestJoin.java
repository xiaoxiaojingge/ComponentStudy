package com.itjing.state;

/**
 * 可以想象成插队，强制执行，必须等join的线程执行了以后，其它线程才能继续执行，测试join方法
 */
public class TestJoin {

    public static void main(String[] args) throws InterruptedException {
        MyJoin join = new MyJoin();
        Thread thread = new Thread(join);
        thread.start();
        for (int i = 1; i <= 500; i++) {
            //200之前main线程和thread交替执行，200以后，main让出时间片让thread执行
            //待thread执行完成后，main线程才继续执行
            if (i == 200) {
                thread.join();
            }
            System.out.println("主线程正在执行" + i);
        }
    }
}

class MyJoin implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i <= 1000; i++) {
            System.out.println("Vip线程来了" + i);
        }
    }
}
