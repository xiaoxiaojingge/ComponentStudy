package com.itjing;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 线程总结
 */
public class TestThreadSummary {
    public static void main(String[] args) {
        new MyThread1().start();
        new Thread(new MyThread2()).start();

        FutureTask<Integer> task = new FutureTask<>(new MyThread3());
        new Thread(task).start();
        try {
            System.out.println(task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

//1.集成Thread类
class MyThread1 extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread1");
    }
}

//2.实现Runnable接口
class MyThread2 implements Runnable {

    @Override
    public void run() {
        System.out.println("MyThread2");
    }
}

//3.实现Callable接口
class MyThread3 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("MyThread3");
        return 100;
    }
}