package com.itjing.designpatterns.singleton.单例设计模式饿汉式之静态常量;

/**
 * @author: lijing
 * @Date: 2021/05/12 17:08
 * @Description: 单例设计模式饿汉式之静态常量
 */
public class SingletonTest {
    public static void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1 == instance2); // true
        System.out.println("instance1.hashCode=" + instance1.hashCode());
        System.out.println("instance2.hashCode=" + instance2.hashCode());
    }
}

/**
 * 饿汉式（静态常量）应用实例步骤
 * 1、构造器私有化
 * 2、类的内部创建静态私有对象
 * 3、向外暴露一个静态的公共方法，获取实例
 */
class Singleton {

    private Singleton() {
    }

    private static Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }
}
/*
    优缺点说明：
    1) 优点：这种写法比较简单，就是在类装载的时候就完成实例化。避免了线程同步问题

    2) 缺点：在类装载的时候就完成实例化，没有达到 Lazy Loading 的效果。如果从始至终从未使用过这个实例，则会造成内存的浪费

    3) 这种方式基于 classloder 机制避免了多线程的同步问题，不过，instance 在类装载时就实例化，
       在单例模式中大多数都是调用 getInstance 方法， 但是导致类装载的原因有很多种，
       因此不能确定有其他的方式（或者其他的静态方法）导致类装载，这时候初始化 instance 就没有达到 lazy loading 的效果

    4) 结论：这种单例模式可用，可能造成内存浪费
 */