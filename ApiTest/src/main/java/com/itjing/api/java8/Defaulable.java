package com.itjing.api.java8;

@FunctionalInterface
public interface Defaulable {
    void method();

    default String notRequired(){
        return "default";
    }

    static void staticMethod(){

    }
}
