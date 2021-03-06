package com.itjing.designpatterns.singleton.枚举;

/**
 * @author: lijing
 * @Date: 2021/05/12 17:25
 * @Description: 枚举
 */
public class SingletonTest {
    public static void main(String[] args) {

        Singleton instance = Singleton.INSTANCE;
        Singleton instance2 = Singleton.INSTANCE;

        System.out.println(instance == instance2);

        System.out.println("instance.hashCode=" + instance.hashCode());
        System.out.println("instance2.hashCode=" + instance2.hashCode());

        instance.sayOK();
    }
}

//使用枚举，可以实现单例, 推荐
enum Singleton {

    INSTANCE; //属性

    public void sayOK() {
        System.out.println("ok~");
    }
}
/*
    优缺点说明：

    1) 这借助 JDK1.5 中添加的枚举来实现单例模式。不仅能避免多线程同步问题，而且还能防止反序列化重新创建新的对象。

    2) 这种方式是 Effective Java 作者 Josh Bloch  提倡的方式

    3) 结论：推荐使用
 */