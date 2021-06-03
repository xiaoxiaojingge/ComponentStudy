## 策略模式引入

参考文章：[点我跳转](https://blog.csdn.net/weixin_44991304/article/details/117355320?utm_medium=distribute.pc_category.none-task-blog-hot-3.nonecase&depth_1-utm_source=distribute.pc_category.none-task-blog-hot-3.nonecase)

在软件开发中，我们常常会遇到这样的情况，实现某一个功能有多条途径，每一条途径对应一种算法，此时我们可以使用一种设计模式来实现灵活地选择解决途径，也能够方便地增加新的解决途径。

譬如商场购物场景中，有些商品按原价卖，商场可能为了促销而推出优惠活动，有些商品打九折，有些打八折，有些则是返现10元等。而优惠活动并不影响结算之外的其他过程，只是在结算的时候需要根据优惠方案结算。
再比如不同的人出去旅游出行的交通方式也不同，经济条件好的会选择高铁飞机，而普通人可能会选择绿皮火车。

富豪老王打算去西藏旅游，老王定了豪华酒店，并且定了机票当天直达。而普通人老张也要去西藏旅游，他打算选择乘坐高铁出行。而学生党的我小汪肯定会选择绿皮火车，主要是为了看路边的风景，而不是因为穷。

下面我们用代码来描述一下上诉场景：

```java
public class Travel {
    private String vehicle;//出行方式
    private String name;

    public String getName() {
        return name;
    }

    public Travel(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void TravelTool(){
        if(name.equals("小汪")){
            setVehicle("绿皮火车");
        }else if(name.equals("老张")){
            setVehicle("高铁");
        }else if(name.equals("老王")){
            setVehicle("飞机");
        }
        System.out.println(name+"选择坐"+getVehicle()+"去西藏旅游");
    }

}

public class Test {
    public static void main(String[] args) {
        Travel travel1 = new Travel("小汪");
        Travel travel2 = new Travel("老王");
        Travel travel3 = new Travel("老张");
        travel1.TravelTool();
        travel2.TravelTool();
        travel3.TravelTool();

    }
}
// 小汪选择坐绿皮火车去西藏旅游
// 老王选择坐飞机去西藏旅游
// 老张选择坐高铁去西藏旅游
```

以上代码虽然完成了我们的需求，但是存在以下问题：

Travel类的TravelTool方法非常庞大，它包含各种人的旅行实现代码，在代码中出现了较长的 if…else… 语句，假如日后小汪发达了也想体验一下做飞机去西藏旅游，那就要去修改TravelTool方法。违反了 “开闭原则”，系统的灵活性和可扩展性较差。
算法的复用性差，如果在另一个系统中需要重用某些算法，只能通过对源代码进行复制粘贴来重用，无法单独重用其中的某个或某些算法。



## 策略模式

策略模式的介绍

- 策略模式（Strategy Pattern）中，定义算法族，分别封装起来，让他们之间可以互相替换，此模式让算法的变化独立于使用算法的客户。
- 这算法体现了几个设计原则，第一、把变化的代码从不变的代码中分离出来；第二、针对接口编程而不是具体类（定义了策略接口）；第三、多用组合/聚合，少用继承（客户通过组合方式使用策略）。

策略模式的原理类图

![在这里插入图片描述](https://gitee.com/xiaojinggege/blogImage/raw/master/img/20210528112731226.png)

角色分析
`Context`（环境类）：环境类是使用算法的角色，它在解决某个问题（即实现某个方法）时可以采用多种策略。在环境类中维持一个对抽象策略类的引用实例，用于定义所采用的策略。

`Strategy`（抽象策略类）：它为所支持的算法声明了抽象方法，是所有策略类的父类，它可以是抽象类或具体类，也可以是接口。环境类通过抽象策略类中声明的方法在运行时调用具体策略类中实现的算法。

`ConcreteStrategy`（具体策略类）：它实现了在抽象策略类中声明的算法，在运行时，具体策略类将覆盖在环境类中定义的抽象策略类对象，使用一种具体的算法实现某个业务处理。

我们下面用策略模式来改进一下上面旅行的代码例子。

抽象策略类 Discount

```java
public abstract class AbstractTravle {
    private String vehicle;
    private String name;

    public AbstractTravle(String vehicle, String name) {
        this.vehicle = vehicle;
        this.name = name;
    }

    public String getVehicle() {
        return vehicle;
    }

    public String getName() {
        return name;
    }

    public abstract void TravelTool();
}
```

ConcreteStrategy（具体策略类）

```java
public class XiaoWang extends AbstractTravle{
    public XiaoWang(String vehicle, String name) {
        super(vehicle, name);
    }

    @Override
    public void TravelTool() {
        System.out.println(getName()+"选择坐"+getVehicle()+"去西藏旅游");
    }
}
public class LaoWang extends AbstractTravle{
    public LaoWang(String vehicle, String name) {
        super(vehicle, name);
    }

    @Override
    public void TravelTool() {
        System.out.println(getName()+"选择坐"+getVehicle()+"去西藏旅游");
    }
}
public class LaoZhang extends AbstractTravle{
    public LaoZhang(String vehicle, String name) {
        super(vehicle, name);
    }
    @Override
    public void TravelTool() {
        System.out.println(getName()+"选择坐"+getVehicle()+"去西藏旅游");
    }

}
```

环境类

```java
public class Context {
    private AbstractTravle abstractTravle;

    public Context(AbstractTravle abstractTravle) {
        this.abstractTravle = abstractTravle;
    }
    public void TravelTool() {
        System.out.println(abstractTravle.getName()+"选择坐"+abstractTravle.getVehicle()+"去西藏旅游");
    }
}
```

测试

```java
public class Test {
    public static void main(String[] args) {
        Context context1 = new Context(new LaoWang("飞机", "老王"));
        context1.TravelTool();
        Context context2 = new Context(new LaoZang("高铁", "老张"));
        context2.TravelTool();
        Context context3 = new Context(new XiaoWang("绿皮火车", "小汪"));
        context3.TravelTool();
    }
}
// 老王选择坐飞机去西藏旅游
// 老张选择坐高铁去西藏旅游
// 小汪选择坐绿皮火车去西藏旅游
```

**策略模式的主要优点如下：**

1. 策略模式提供了对 “开闭原则” 的完美支持，用户可以在不修改原有系统的基础上选择算法或行为，也可以灵活地增加新的算法或行为。
2. 策略模式提供了管理相关的算法族的办法。策略类的等级结构定义了一个算法或行为族，恰当使用继承可以把公共的代码移到抽象策略类中，从而避免重复的代码。
3. 策略模式提供了一种可以替换继承关系的办法。如果不使用策略模式而是通过继承，这样算法的使用就
   和算法本身混在一起，不符合 “单一职责原则”，而且使用继承无法实现算法或行为在程序运行时的动态切
   换。
4. 使用策略模式可以避免多重条件选择语句。多重条件选择语句是硬编码，不易维护。
5. 策略模式提供了一种算法的复用机制，由于将算法单独提取出来封装在策略类中，因此不同的环境类可以方便地复用这些策略类。

**策略模式的主要缺点如下：**

1. 客户端必须知道所有的策略类，并自行决定使用哪一个策略类。这就意味着客户端必须理解这些算法的区别，以便适时选择恰当的算法。换言之，策略模式只适用于客户端知道所有的算法或行为的情况。
2. 策略模式将造成系统产生很多具体策略类，任何细小的变化都将导致系统要增加一个新的具体策略类。
3. 无法同时在客户端使用多个策略类，也就是说，在使用策略模式时，客户端每次只能使用一个策略类，不支持使用一个策略类完成部分功能后再使用另一个策略类来完成剩余功能的情况。
   
   

**适用场景**

1. 一个系统需要动态地在几种算法中选择一种，那么可以将这些算法封装到一个个的具体算法类中，而这些具体算法类都是一个抽象算法类的子类。换言之，这些具体算法类均有统一的接口，根据 “里氏代换原则” 和面向对象的多态性，客户端可以选择使用任何一个具体算法类，并只需要维持一个数据类型是抽象算法类的对象。
2. 一个对象有很多的行为，如果不用恰当的模式，这些行为就只好使用多重条件选择语句来实现。此时，使用策略模式，把这些行为转移到相应的具体策略类里面，就可以避免使用难以维护的多重条件选择语句。
3. 不希望客户端知道复杂的、与算法相关的数据结构，在具体策略类中封装算法与相关的数据结构，可以提高算法的保密性与安全性。
   
   