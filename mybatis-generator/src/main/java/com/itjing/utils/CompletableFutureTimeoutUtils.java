package com.itjing.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.*;
import java.util.function.BiConsumer;

import static java.util.Objects.isNull;

/**
 * @author lijing
 * @date 2023-07-13
 */
@Slf4j
public class CompletableFutureTimeoutUtils {

    /**
     * 单例延时调度程序，仅用于启动和取消任务
     */
    static final class Delayer {
        static final class DaemonThreadFactory implements ThreadFactory {
            @Override
            public Thread newThread(@NonNull Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                t.setName("CompletableFutureDelayScheduler");
                return t;
            }
        }

        static final ScheduledThreadPoolExecutor delayer;

        // 注意，这里使用一个线程就可以搞定 因为这个线程并不真的执行请求 仅仅只是用于启动和取消任务
        static {
            (delayer = new ScheduledThreadPoolExecutor(
                    1, new Delayer.DaemonThreadFactory())).
                    setRemoveOnCancelPolicy(true);
        }

        static ScheduledFuture<?> delay(Runnable command, long delay, TimeUnit unit) {
            return delayer.schedule(command, delay, unit);
        }
    }

    /**
     * 超时时以抛异常的形式结束任务
     */
    static final class Timeout implements Runnable {
        final CompletableFuture<?> f;

        Timeout(CompletableFuture<?> f) {
            this.f = f;
        }

        @Override
        public void run() {
            if (f != null && !f.isDone()) {
                f.completeExceptionally(new TimeoutException());
            }
        }
    }

    /**
     * 在超时时完成
     */
    static final class DelayedCompleter<U> implements Runnable {
        final CompletableFuture<U> f;
        final U u;

        DelayedCompleter(CompletableFuture<U> f, U u) {
            this.f = f;
            this.u = u;
        }

        @Override
        public void run() {
            if (f != null) {
                f.complete(u);
            }
        }
    }

    /**
     * 取消不需要的超时任务
     */
    static final class Canceller implements BiConsumer<Object, Throwable> {
        final Future<?> f;

        Canceller(Future<?> f) {
            this.f = f;
        }

        @Override
        public void accept(Object ignore, Throwable ex) {
            if (ex == null && f != null && !f.isDone()) {
                f.cancel(false);
            }
        }
    }

    @SuppressWarnings("unchecked")
    static <R, T extends Throwable> R typeErasure(final Throwable throwable) throws T {
        throw (T) throwable;
    }

    static <T> T timeoutDefault(Throwable throwable, T value) {
        final Deque<Throwable> queue = new ArrayDeque<>();
        while (throwable != null && !queue.contains(throwable)) {
            queue.push(throwable);
            throwable = throwable.getCause();
        }
        final Throwable cause = queue.peek();
        if (cause instanceof TimeoutException) {
            return value;
        }
        return CompletableFutureTimeoutUtils.<T, RuntimeException>typeErasure(cause);
    }

    public static <T> CompletableFuture<T> timeoutAfter(long timeout, @NonNull TimeUnit unit) {
        CompletableFuture<T> result = new CompletableFuture<>();
        Delayer.delay(() -> result.completeExceptionally(new TimeoutException()), timeout, unit);
        return result;
    }

    public static <T> CompletableFuture<T> completeOnTimeout(@NonNull CompletableFuture<T> future, T value, long timeout, @NonNull TimeUnit unit) {
        // migrate from jdk9
        if (isNull(future.getNow(null))) {
            future.whenComplete(new Canceller(Delayer.delay(new DelayedCompleter<>(future, value), timeout, unit)));
        }
        return future;

        // another implementation
//        return future.applyToEither(timeoutAfter(timeout, unit), Function.identity())
//                .exceptionally(ex -> timeoutDefault(ex, value));
    }

    public static <T> CompletableFuture<T> orTimeout(@NonNull CompletableFuture<T> future, long timeout, @NonNull TimeUnit unit) {
        // migrate from jdk9
        if (isNull(future.getNow(null))) {
            future.whenComplete(new Canceller(Delayer.delay(new Timeout(future), timeout, unit)));
        }
        return future;

        // another implementation
//        return future.applyToEither(timeoutAfter(timeout, unit), Function.identity());
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "正常";
        });
//        CompletableFuture<String> within = completeOnTimeout(future, "超时", 3, TimeUnit.SECONDS);
        CompletableFuture<String> within = orTimeout(future, 1, TimeUnit.SECONDS);
        log.info(within.get());
    }

}
