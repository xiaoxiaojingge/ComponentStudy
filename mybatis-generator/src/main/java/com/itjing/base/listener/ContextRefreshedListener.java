package com.itjing.base.listener;

import com.itjing.base.annotation.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author lijing
 * @date 2021年11月13日 9:45
 * @description
 */
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContextRefreshedListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        LOGGER.debug(">>>>> spring初始化完毕 <<<<<");
        // spring初始化完毕后，通过反射调用所有使用BaseService注解的initMapper方法
        Map<String, Object> baseServices = contextRefreshedEvent.getApplicationContext().getBeansWithAnnotation(BaseService.class);
        for (Object service : baseServices.values()) {
            LOGGER.debug(">>>>> {}.initMapper()", service.getClass().getName());
            try {
                Method initMapper = service.getClass().getMethod("initMapper");
                initMapper.invoke(service);
            } catch (Exception e) {
                LOGGER.error("初始化BaseService的initMapper方法异常", e);
                e.printStackTrace();
            }
        }
    }
}
