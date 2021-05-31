package com.itjing.api.deginmodel.test;


import com.itjing.api.deginmodel.observer.Child;
import com.itjing.api.deginmodel.observer.Dad;
import com.itjing.api.deginmodel.observer.GrandFather;
import org.junit.Test;

/**
 * 作者: sanri
 * 时间: 2017/08/03 11:11
 * 功能: 观察者模式测试
 */
public class ObserverTest {

    @Test
    public void testObserver(){
        Dad dad = new Dad();
        GrandFather grandFather = new GrandFather();
        Child child = new Child("小强");

        //添加注册
        child.registerObserver(dad);
        child.registerObserver(grandFather);

        child.wakeup();
    }
}
