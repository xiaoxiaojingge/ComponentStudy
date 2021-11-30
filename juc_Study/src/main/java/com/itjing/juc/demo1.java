package com.itjing.juc;

import java.util.concurrent.TimeUnit;

public class demo1 {
    public static void main(String[] args) throws InterruptedException {
        // 获取cpu的核数
        // CPU 密集型，IO密集型
        System.out.println(Runtime.getRuntime().availableProcessors());
        TimeUnit.SECONDS.sleep(1);
    }
}
