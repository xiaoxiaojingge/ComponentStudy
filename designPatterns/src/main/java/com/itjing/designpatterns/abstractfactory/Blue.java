package com.itjing.designpatterns.abstractfactory;

/**
 * @author: lijing
 * @Date: 2021年05月27日 16:38
 * @Description:
 */
public class Blue implements Color {
    @Override
    public void fill() {
        System.out.println("The color is blue");
    }
}
