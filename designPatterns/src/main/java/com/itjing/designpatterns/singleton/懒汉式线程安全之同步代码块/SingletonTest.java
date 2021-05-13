package com.itjing.designpatterns.singleton.懒汉式线程安全之同步代码块;

/**
 * @author: lijing
 * @Date: 2021/05/12 17:25
 * @Description: 懒汉式线程安全之同步代码块
 */
public class SingletonTest {
    public static void main(String[] args) {
        System.out.println("懒汉式 3 ， 线程安全~");
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance == instance2); // true
        System.out.println("instance.hashCode=" + instance.hashCode());
        System.out.println("instance2.hashCode=" + instance2.hashCode());
    }
}

class Singleton {

    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            // 同步代码块
            synchronized (Singleton.class) {
                instance = new Singleton();
            }
        }
        return instance;
    }
}
/*
    结论：在实际开发中，不推荐使用这种方式
 */