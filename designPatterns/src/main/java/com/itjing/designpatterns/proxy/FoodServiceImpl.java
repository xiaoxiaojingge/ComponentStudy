package com.itjing.designpatterns.proxy;

/**
 * @author: lijing
 * @Date: 2021年05月27日 17:03
 * @Description: 被代理实现类就只需要做自己该做的事情就好了，不需要管别的。
 */
public class FoodServiceImpl implements FoodService {
    @Override
    public Food makeChicken() {
        Food f = new Chicken();
        f.setChicken("1kg");
        f.setSpicy("1g");
        f.setSalt("3g");
        System.out.println("鸡肉加好佐料了");
        return f;
    }
}
