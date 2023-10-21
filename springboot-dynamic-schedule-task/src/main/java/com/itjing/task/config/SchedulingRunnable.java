package com.itjing.task.config;

import com.itjing.utils.SpringContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * @Description Runnable接口实现类，被定时任务线程池调用，用来执行指定bean里面的方法
 * @Author lijing
 * @Date 2023-10-21 12:17
 */
public class SchedulingRunnable implements Runnable {

    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(SchedulingRunnable.class);

    /**
     * spring容器中管理的bean名称
     */
    private String beanName;

    /**
     * bean中方法名称
     */
    private String methodName;

    /**
     * 业务id
     */
    private Integer businessId;

    /**
     * 方法参数
     */
    private LinkedHashMap<String, Object> params;

    public SchedulingRunnable(String beanName, String methodName) {
        this(beanName, methodName, null);
    }

    public SchedulingRunnable(String beanName, String methodName, LinkedHashMap<String, Object> params) {
        this.beanName = beanName;
        this.methodName = methodName;
        this.params = params;
    }

    @Override
    public void run() {
        logger.info("定时任务开始执行 - bean：{}，方法：{}，参数：{}", beanName, methodName, params);
        long startTime = System.currentTimeMillis();
        try {
            Object target = SpringContextUtils.getBean(beanName);
            Method method;
            if (Objects.nonNull(params)) {
                Class<?>[] paramTypes = new Class<?>[params.size()];
                int i = 0;
                for (Object value : params.values()) {
                    paramTypes[i++] = value.getClass();
                }
                method = target.getClass().getDeclaredMethod(methodName, paramTypes);
            } else {
                method = target.getClass().getDeclaredMethod(methodName);
            }
            ReflectionUtils.makeAccessible(method);
            if (Objects.nonNull(params)) {
                Object[] args = new Object[params.size()];
                int i = 0;
                for (Object value : params.values()) {
                    args[i++] = value;
                }
                method.invoke(target, args);
            } else {
                method.invoke(target);
            }
        } catch (Exception ex) {
            logger.error(String.format("定时任务执行异常 - bean：%s，方法：%s，参数：%s ", beanName, methodName, params), ex);
            return;
        }
        long times = System.currentTimeMillis() - startTime;
        logger.info("定时任务执行结束 - bean：{}，方法：{}，参数：{}，耗时：{} 毫秒", beanName, methodName, params, times);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SchedulingRunnable that = (SchedulingRunnable) o;
        if (this.params == null) {
            return this.beanName.equals(that.getBeanName()) && this.methodName.equals(that.getMethodName()) && that.getParams() == null;
        }
        return this.beanName.equals(that.getBeanName()) && this.methodName.equals(that.getMethodName()) && this.params.equals(that.getParams());
    }

    @Override
    public int hashCode() {
        if (params == null) {
            return Objects.hash(beanName, methodName);
        }
        return Objects.hash(beanName, methodName, params);
    }


    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public LinkedHashMap<String, Object> getParams() {
        return params;
    }

    public void setParams(LinkedHashMap<String, Object> params) {
        this.params = params;
    }

}