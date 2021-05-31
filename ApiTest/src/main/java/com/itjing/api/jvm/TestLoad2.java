package com.itjing.api.jvm;

public class TestLoad2 {

    {
        System.out.println("哪个先执行2");
    }
    public TestLoad2(){
        System.out.println("构造函数");
    }

    {
        System.out.println("哪个先执行1");
    }
}
