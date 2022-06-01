package com.itjing.singleton.dcl;

/**
 * @author lijing
 * @date 2022年06月01日 19:53
 * @description 双重检查锁实现单例模式
 */
public class Singleton {

    private static volatile Singleton singleton;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
