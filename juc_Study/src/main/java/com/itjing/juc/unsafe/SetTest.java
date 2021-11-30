package com.itjing.juc.unsafe;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author lijing
 * @Description
 * @create 2020-11-20 11:56
 */
public class SetTest {
    public static void main(String[] args) {
        //同理可证 ： ConcurrentModificationException
//        Set<String> set = new HashSet<>();


        //解决
        //Set<String> set = Collections.synchronizedSet(new HashSet<>());
        //Set<String> set = new CopyOnWriteArraySet<>();
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
