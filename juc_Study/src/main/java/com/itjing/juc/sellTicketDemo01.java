package com.itjing.juc;

/**
 * @author lijing
 * @Description 基本的买票例子
 * 真正的多线程开发，公司中的开发，
 * 线程就是一个单独的资源类，没有任何附属的操作
 * 里面有：
 * 1、 属性、方法
 * @create 2020-11-19 18:54
 */
public class sellTicketDemo01 {
    public static void main(String[] args) {
        // 并发：多线程操作同一个资源类, 把资源类丢入线程
        Ticket ticket = new Ticket();

        // @FunctionalInterface 函数式接口
        // jdk1.8  lambda表达式 (参数)->{ 代码 }
        new Thread(() -> {
            for (int i = 1; i <= 40; i++) {
                ticket.sale();
            }
        },"A").start();

        new Thread(() -> {
            for (int i = 1; i <= 40; i++) {
                ticket.sale();
            }
        },"B").start();

        new Thread(() -> {
            for (int i = 1; i <= 40; i++) {
                ticket.sale();
            }
        },"C").start();
    }
}

//资源类  OOP编程
class Ticket {
    //属性、方法
    private int number = 50;

    //买票的方式
    //传统的方式：加synchronized
    // synchronized 本质: 队列，锁
    public synchronized void sale() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出了第" + (number--) + "票，剩余" + number + "票。");
        }
    }
}
