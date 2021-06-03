## 观察者模式

消息队列（MQ），一种能实现生产者到消费者单向通信的通信模型，这也是现在常用的主流中间件。常见有 RabbitMQ、ActiveMQ、Kafka等  他们的特点也有很多 比如 **解偶**、**异步**、**广播**、**削峰** 等等多种优势特点。

在设计模式中也有一种模式能有效的达到**解偶**、**异步**的特点，那就是**观察者模式**又称为**发布订阅模式**。

### 定义

什么是观察者模式？他的目的是什么？

> 当一个对象的状态发生改变时，已经登记的其他对象能够观察到这一改变从而作出自己相对应的改变。通过这种方式来达到减少依赖关系，解耦合的作用。

举一个例子，就好比微信朋友圈，以当前个人作为订阅者，好友作为主题。一个人发一条动态朋友圈出去，他的好友都能看到这个朋友圈，并且可以在自主选择点赞或者评论。

感觉有点抽象，还是看看他有哪些主要角色：



![img](https://gitee.com/xiaojinggege/blogImage/raw/master/img/efc9118cb7934600ac876d3d31876916~tplv-k3u1fbpfcp-zoom-1.image)

> - Subject（主题）: 主要由类实现的可观察的接口，通知观察者使用attach方法，以及取消观察的detach方法。
> - ConcreteSubject（具体主题）: 是一个实现主题接口的类，处理观察者的变化
> - Observe（观察者）: 观察者是一个由对象水岸的接口，根据主题中的更改而进行更新。

这么看角色也不多，但是感觉还是有点抽象，我们还是用具体实例代码来走一遍吧，我们还是以上面的朋友圈为例看看代码实现

```java
public interface Subject {
    // 添加订阅关系
    void attach(Observer observer);
    // 移除订阅关系
    void detach(Observer observer);
    // 通知订阅者
    void notifyObservers(String message);
}
```

先创建一个主题定义，定义添加删除关系以及通知订阅者

```java
public class ConcreteSubject implements Subject {

    // 订阅者容器
    private List<Observer> observers = new ArrayList<Observer>();

    @Override
    public void attach(Observer observer) {
        // 添加订阅关系
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        // 移除订阅关系
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        // 通知订阅者们
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
```

其次再创建的具体主题，并且构建一个容器来维护订阅关系，支持添加删除关系，以及通知订阅者

```java
public interface Observer {
    // 处理业务逻辑
    void update(String message);
}
```

创建一个观察者接口，方便我们管理

```java
public class FriendOneObserver implements Observer {
   
  @Override
    public void update(String message) {
        // 模拟处理业务逻辑
        System.out.println("FriendOne 知道了你发动态了" + message);
    }
}
```

最后就是创建具体的观察者类，实现观察者接口的update方法，处理本身的业务逻辑

```java
public class test {
    
    public static void main(String[] args) {

        ConcreteSubject subject = new ConcreteSubject();
        // 这里假设是添加好友
        subject.attach(new FriendOneObserver());
        FriendTwoObserver twoObserver = new FriendTwoObserver();
        subject.attach(twoObserver);

        // 发送朋友圈动态
        subject.notifyObservers("第一个朋友圈消息");
        // 输出结果： FriendOne 知道了你发动态了第一个朋友圈消息
        //          FriendTwo 知道了你发动态了第一个朋友圈消息

        // 这里发现 twoObserver 是个推荐卖茶叶的，删除好友
        subject.detach(twoObserver);
        subject.notifyObservers("第二个朋友圈消息");
        // 输出结果：FriendOne 知道了你发动态了第二个朋友圈消息
    }
}
```

最后就是看测试结果了，通过ConcreteSubject 维护了一个订阅关系，在通过notifyObservers 方法通知订阅者之后，观察者都获取到消息从而处理自己的业务逻辑。

![img](https://gitee.com/xiaojinggege/blogImage/raw/master/img/7abf1ba63b514b2da30a6385a46ddf96~tplv-k3u1fbpfcp-zoom-1.image)

这里细心的朋友已经达到了解耦合的效果，同时也减少了依赖关系，每个观察者根本不要知道发布者处理了什么业务逻辑，也不用依赖发布者任何业务模型，只关心自己本身需要处理的逻辑就可以了。

如果有新的业务添加进来，我们也只需要创建一个新的订阅者，并且维护到observers 容器中即可，也符合我们的开闭原则。

这里只是一种同步的实现方式，我们还可以扩展更多其他的异步实现方式，或者采用多线程等实现方式。

### 框架应用

观察者模式在框架的中的应用也是应该很多

- 第一种  熟悉JDK的人应该知道 在java.util 包下 除了常用的 集合 和map之外还有一个**Observable**类，他的实现方式其实就是观察者模式。里面也有**添加、删除、通知**等方法。

  这里需要注意是的 他是用Vector 作为订阅关系的容器，同时在他的定义方法中都添加synchronized关键字修饰类，以达到线程安全的目的

  这里我贴出了关键源码，感兴趣的同学可以自己打开并且观看每个方法的注释。

![img](https://gitee.com/xiaojinggege/blogImage/raw/master/img/128ee7e134264a9a8ec9586cfd97aff4~tplv-k3u1fbpfcp-zoom-1.image)

- 第二种 在Spring中有一个ApplicationListener，也是采用观察者模式来处理的，ApplicationEventMulticaster作为主题，里面有添加，删除，通知等。

  spring有一些内置的事件，当完成某种操作时会发出某些事件动作，他的处理方式也就上面的这种模式，当然这里面还有很多，我没有细讲，有兴趣的同学可以仔细了解下Spring的启动过程。

```java
import java.util.EventListener;

/**
 * Interface to be implemented by application event listeners.
 * Based on the standard {@code java.util.EventListener} interface
 *  for the Observer design pattern. // 这里也已经说明是采用观察者模式
 *
 * <p>As of Spring 3.0, an ApplicationListener can generically declare the event type
 * that it is interested in. When registered with a Spring ApplicationContext, events
 * will be filtered accordingly, with the listener getting invoked for matching event
 * objects only.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @param <E> the specific ApplicationEvent subclass to listen to
 * @see org.springframework.context.event.ApplicationEventMulticaster //主题
 */
@FunctionalInterface
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

	/**
	 * Handle an application event.
	 * @param event the event to respond to
	 */
	void onApplicationEvent(E event);

}
```

- 第三种  Google Guava的事件处理机制**Guava EventBus** 他的实现也是采用设计模式中的观察者设计模式。

  > EventBus 当前实现有两种方式：
  >
  > - EventBus  // 同步阻塞模式
  > - AsyncEventBus // // 异步非阻塞模式

EventBus内部也提供来一系列的方法来供我们方便使用：

- register 方法作为添加观察者
- unregister方法删除观察者
- post 方法发送通知消息等

使用起来非常方便。添加@Subscribe注解就可以创建一个订阅者了，具体的使用方式可以看看官网。

### 现实业务改造举例

框架应用的例子这么多，在业务场景中其实也有很多地方可以使用到，这里我还是给大家举一个例子。

在新用户注册成功之后我们需要给用户做两件事情，第一是发送注册成功短信，第二是给用发送新人优惠券。

看到这个问题 大家可能首先会想到用MQ消息处理呀，是的，用消息确实可以的，但是这里我们用观察者模式来实现这个问题，同时可以给大家演示一下，同步或者异步的问题。

```java
public class SendNewPersonCouponObserver implements Observer {

    ExecutorService pool = Executors.newFixedThreadPool(2);

    @Override
    public void update(String message) {

        Future<String> future = pool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(3);
                // 处理响应的业务逻辑
                return "调用发券服务，返回结果";
            }
        });
        try {
            // 假设等待200毫秒 没有获取到返回值结果则认为失败
            System.out.println(future.get(4000, TimeUnit.MILLISECONDS));
        } catch (Exception e) {
            // 执行异步获取失败
            // 记录日志，定时任务重试等
        }

        // 第一种不关心返回值结果
        Thread thread = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                // 模拟服务调用 线程睡3秒钟
                TimeUnit.SECONDS.sleep(3);
                System.out.println("发送新人优惠券");
            }
        });
        thread.start();
        System.out.println("执行异步返回");
    }
}
```

```java
public class SendSuccessMessageObserver implements Observer {

    @Override
    public void update(String message) {
        // 处理业务逻辑
        System.out.println("注册成功");
    }

    public static void main(String[] args) {
        // 假设用户注册成功直接通知观察者，改干自己的事情了
        ConcreteSubject subject = buildSubject();
        subject.notifyObservers("");
    }
 
   private static ConcreteSubject buildSubject() {
        ConcreteSubject subject = new ConcreteSubject();
        subject.attach(new SendSuccessMessageObserver());
        subject.attach(new SendNewPersonCouponObserver());
        return subject;
    }
}
```

![img](https://gitee.com/xiaojinggege/blogImage/raw/master/img/f9ef94218651462685990bb0167fdfb9~tplv-k3u1fbpfcp-zoom-1.image)

这里我们新写了两个观察者，主要看第一个`SendNewPersonCouponObserver`，这里了异步开启新的线程去处理我们的业务逻辑，当我们关心返回值的时候可以用Future来获取返回结果，当不关心的返回值的化，直接开启普通线程就可以了。

这个举例整体其实还是比较简单的主要是为了说清楚异步线程处理，当然如果用Guava EventBus也可以实现。而且也不复杂，感兴趣的朋友可以自己去试试。

当前现在有更加好的中间件**MQ消息队列**来处理这个业务问题，使得我们更加从容的面对这类场景问题，但是一些资源不足，不想引入新的系统。还是可以用这种方式来处理问题的。

设计模式学习的不是代码，而是学习每种模式的思想，他们分别处理的是什么业务场景。

### 总结

大家看完本篇文章不知道有发现没有，其实整个内容都是围绕了**解耦**的思想来写的，观察者模式作为行为型设计模式，主要也是为了不同的业务行为的代码**解耦**。

合理的使用设计模式可以使代码结构更加清晰，同时还能满足不同的小模块符合单一职责，以及开闭原则，从而达到前面写工厂模式说的，提高代码的可扩展性，维护成本低的特点。