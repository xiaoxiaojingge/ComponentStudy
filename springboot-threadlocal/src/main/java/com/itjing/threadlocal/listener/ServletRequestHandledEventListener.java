package com.itjing.threadlocal.listener;

import com.itjing.threadlocal.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletRequestHandledEvent;

/**
 * @author lijing
 * @date 2022年05月31日 19:12
 * @description ServletRequest请求监听器
 */
@Component
@Slf4j
public class ServletRequestHandledEventListener implements ApplicationListener<ServletRequestHandledEvent> {

    @Override
    public void onApplicationEvent(ServletRequestHandledEvent event) {
        CurrentUser.removeAll();
        log.debug(
                "清除当前线程用户信息,uri = {},method = {},servletName = {},clientAddress = {}",
                event.getRequestUrl(),
                event.getMethod(),
                event.getServletName(),
                event.getClientAddress()
        );
    }
}
