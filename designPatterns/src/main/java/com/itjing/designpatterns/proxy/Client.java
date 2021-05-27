package com.itjing.designpatterns.proxy;

/**
 * @author: lijing
 * @Date: 2021年05月27日 17:11
 * @Description: 客户端
 */
public class Client {
    public static void main(String[] args) {
        // 注意，我们要用代理来实例化接口
        FoodService foodService = new FoodServiceProxy();
        foodService.makeChicken();
    }
}
