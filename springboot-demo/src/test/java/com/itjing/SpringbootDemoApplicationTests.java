package com.itjing;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.ref.*;

@SpringBootTest
class SpringbootDemoApplicationTests {

    @Test
    void contextLoads() {
    }


    /**
     * 软引用
     */
    @Test
    void testSoftReference() {
        SoftReference<String> softReference = new SoftReference<>("lijing");
        System.out.println(softReference.get());
        System.gc();
        System.out.println(softReference.get());
    }

    /**
     * 弱引用
     */
    @Test
    void testWeakReference() {
        WeakReference<String> weakReference = new WeakReference<>("lijing");
        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());
    }

    class User {
        private Integer id;
        private String name;

        public User(Integer id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    /**
     * 虚引用
     */
    @Test
    void testPhantomReference() {
        User user = new User(1, "lijing");
        ReferenceQueue<User> userReferenceQueue = new ReferenceQueue<>();
        // 创建User对象的虚引用
        PhantomReference<User> phantomReference = new PhantomReference<>(user, userReferenceQueue);
        // 去掉强引用
        user = null;
        System.out.println(phantomReference.get());
        // 手动触发GC
        System.gc();
        System.out.println("GC: " + phantomReference.get());
        Reference<? extends User> reference = null;
        try {
            reference = userReferenceQueue.remove(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (reference != null) {
            System.out.println("对象User被回收了！");
        }
    }
}
