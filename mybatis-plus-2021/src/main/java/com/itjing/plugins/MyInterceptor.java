package com.itjing.plugins;

/**
 * @author lijing
 * @date 2021年12月08日 17:23
 * @description
 */

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Statement;
import java.util.Properties;

/**
 * //定义拦截那个对象的那个方法的那个参数
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "parameterize", args = Statement.class)})
public class MyInterceptor implements Interceptor {

    /**
     * 拦截目标对象的目标方法的执行
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("--------MyInterceptor.intercept------------" + invocation.getMethod());
        //执行目标方法
        Object proceed = invocation.proceed();
        return proceed;
    }

    /**
     * 包装目标对象：为目标对象创建一个代理对象
     *
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        System.out.println("------MyInterceptor--plugin-------" + target);

        //借助Plugin的wrap使用当前的拦截器包装目标对象
        Object wrap = Plugin.wrap(target, this);
        //为当前target创建的动态代理
        return wrap;
    }

    /**
     * 将插件注册时的properties属性设置进来
     *
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        System.out.println("插件配置的信息 = " + properties);
    }
}
