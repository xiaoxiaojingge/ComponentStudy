package com.itjing.task.config;

import java.util.concurrent.ScheduledFuture;

/**
 * @Description ScheduledFuture的包装类
 * @Author lijing
 * @Date 2023-10-21 12:13
 */
public final class ScheduledTask {
    
    /**
     * ScheduledFuture 是 ScheduledExecutorService 定时任务线程池的执行结果。
     */
    volatile ScheduledFuture<?> future;
    
    /**
     * 取消定时任务
     */
    public void cancel() {
        ScheduledFuture<?> future = this.future;
        if (future != null) {
            future.cancel(true);
        }
    }
}