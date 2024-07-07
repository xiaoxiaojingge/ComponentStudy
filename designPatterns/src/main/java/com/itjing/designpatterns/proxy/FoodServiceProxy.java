package com.itjing.designpatterns.proxy;

/**
 * @author: lijing
 * @Date: 2021年05月27日 17:06
 * @Description: 代理类
 */
public class FoodServiceProxy implements FoodService {

	// 内部一定要有一个真实的实现类，当然也可以通过构造方法注入
	private FoodService foodService = new FoodServiceImpl();

	@Override
	public Food makeChicken() {
		System.out.println("开始制作鸡肉");

		// 如果我们定义这句为核心代码的话，那么，核心代码是真实实现类做的，
		// 代理只是在核心代码前后做些“无足轻重”的事情
		Food food = foodService.makeChicken();

		System.out.println("鸡肉制作完成啦，加点胡椒粉");
		food.addCondiment("pepper");
		System.out.println("上锅咯");
		return food;
	}

}
