package com.itjing.service.impl;

import com.itjing.service.ContinuousDelayService;
import com.itjing.util.ContinuousDelayBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: ComponentStudy
 * @author: zhanling.li
 * @create: 2022-06-02 10:43
 */
@Slf4j
@Service
public class ContinuousDelayServiceImpl extends ContinuousDelayBaseService<Integer>
        implements ContinuousDelayService {
    private static ExecutorService executor = new ThreadPoolExecutor(10, 20,
            60L, TimeUnit.SECONDS, new ArrayBlockingQueue(10));

    /**
     * 定时任务，启动时执行一次，往后每分钟执行一次,10个线程同时执行
     */
    @Scheduled(fixedDelay = 1000 * 60 * 1)
    public void update() {
        log.info("开始执行定时任务..................");

        executor.submit(() -> this.updateData());
        executor.submit(() -> this.updateData());
        executor.submit(() -> this.updateData());
        executor.submit(() -> this.updateData());
        executor.submit(() -> this.updateData());
        executor.submit(() -> this.updateData());
        executor.submit(() -> this.updateData());
        executor.submit(() -> this.updateData());
        executor.submit(() -> this.updateData());
        executor.submit(() -> this.updateData());
        log.info("结束执行定时任务..................");
    }

    public void updateData() {
        for (int i = 0; i < 10; i++) {
            int updateRows = updateMock();
            if (updateRows > 0) {
                //log.info("更新了{} 条",updateRows);
                super.delay(2000L, (old) -> {
                    if (old == null) {
                        return updateRows;
                    }
                    return updateRows + old;
                });
            }
        }
    }

    private int updateMock() {
        return 2;
    }


    @Override
    protected void done(Integer value) {
        log.info("执行结果----------------> {}", value);
    }
}
