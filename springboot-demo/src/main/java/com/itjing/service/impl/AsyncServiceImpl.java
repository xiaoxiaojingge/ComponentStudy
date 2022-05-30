package com.itjing.service.impl;

import com.itjing.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author lijing
 * @date 2022年05月30日 19:53
 * @description
 */
@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {

    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync() {
        log.info("start executeAsync");
        // 异步线程要做的事情 TODO
        // 可以在这里执行操作比较耗时的事情
        log.info("do something......");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("end executeAsync");
    }
}
