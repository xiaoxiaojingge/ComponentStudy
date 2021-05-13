package com.itjing.designpatterns.singleton.双重检查;

/**
 * @author: lijing
 * @Date: 2021/05/12 17:25
 * @Description: 双重检查
 */
public class SingletonTest {
    public static void main(String[] args) {
        System.out.println("懒汉式 4 ， 线程安全~");
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

    //提供一个静态的公有方法，加入双重检查代码，解决线程安全问题, 同时解决懒加载问题
    //同时保证了效率, 推荐使用
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }

        }
        return instance;
    }
}
/*
    优缺点说明：

    1) Double-Check 概念是多线程开发中常使用到的，如代码中所示，我们进行了两次 if (singleton == null)检查，这样就可以保证线程安全了

    2) 这样，实例化代码只用执行一次，后面再次访问时，判断 if (singleton == null)，直接 return 实例化对象，也避免的反复进行方法同步

    3) 线程安全；延迟加载；效率较高

    4) 结论：在实际开发中，推荐使用这种单例设计模式
 */