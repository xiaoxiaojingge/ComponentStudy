package com.itjing.singleton.innerclass;

/**
 * @author lijing
 * @date 2022年06月01日 20:18
 * @description 静态内部类实现单例模式
 */
public class Singleton {

    private Singleton() {
    }

    private static class SingletonInstance {
        private static Singleton singleton = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonInstance.singleton;
    }
}