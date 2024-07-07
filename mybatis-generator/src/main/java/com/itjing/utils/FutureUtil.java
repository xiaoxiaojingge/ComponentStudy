package com.itjing.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author lijing
 * @date 2023-07-13
 */
@Slf4j
public class FutureUtil {

	/**
	 * cpu 核心数
	 */
	private static final int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

	// 最大超时时间
	private static final int TIMEOUT_VALUE = 1500;

	// 时间单位
	private static final TimeUnit TIMEOUT_UNIT = TimeUnit.MILLISECONDS;

	/**
	 * Singleton delay scheduler, used only for starting and * cancelling tasks.
	 */
	public static final class Delayer {

		static final ScheduledThreadPoolExecutor delayer;

		/**
		 * 异常线程，不做请求处理，只抛出异常
		 */
		static {
			delayer = new ScheduledThreadPoolExecutor(1, new DaemonThreadFactory());
			delayer.setRemoveOnCancelPolicy(true);
		}

		static ScheduledFuture<?> delay(Runnable command, long delay, TimeUnit unit) {
			return delayer.schedule(command, delay, unit);
		}

		static final class DaemonThreadFactory implements ThreadFactory {

			@Override
			public Thread newThread(Runnable r) {
				Thread t = new Thread(r);
				t.setDaemon(true);
				t.setName("CompletableFutureScheduler");
				return t;
			}

		}

	}

	/**
	 * 根据服务器cpu自定义线程池
	 */
	private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(AVALIABLE_PROCESSORS,
			3 * AVALIABLE_PROCESSORS, 3, TimeUnit.SECONDS, new LinkedBlockingDeque<>(20),
			new ThreadPoolExecutor.CallerRunsPolicy());

	/**
	 * 有返回值的异步
	 * @param supplier
	 * @param <T>
	 * @return
	 */
	public static <T> CompletableFuture<T> supplyAsync(Supplier<T> supplier) {
		return supplyAsync(TIMEOUT_VALUE, TIMEOUT_UNIT, supplier);
	}

	/**
	 * 有返回值的异步 - 可设置超时时间
	 * @param timeout
	 * @param unit
	 * @param supplier
	 * @param <T>
	 * @return
	 */
	public static <T> CompletableFuture<T> supplyAsync(long timeout, TimeUnit unit, Supplier<T> supplier) {
		return CompletableFuture.supplyAsync(supplier, threadPoolExecutor)
			.applyToEither(timeoutAfter(timeout, unit), Function.identity())
			.exceptionally(throwable -> {
				throwable.printStackTrace();
				log.error(throwable.getMessage());
				return null;
			});
	}

	/**
	 * 无返回值的异步
	 * @param runnable
	 * @return
	 */
	public static CompletableFuture runAsync(Runnable runnable) {
		return runAsync(TIMEOUT_VALUE, TIMEOUT_UNIT, runnable);
	}

	/**
	 * 无返回值的异步 - 可设置超时时间
	 * @param runnable
	 * @return
	 */
	public static CompletableFuture runAsync(long timeout, TimeUnit unit, Runnable runnable) {
		return CompletableFuture.runAsync(runnable, threadPoolExecutor)
			.applyToEither(timeoutAfter(timeout, unit), Function.identity())
			.exceptionally(throwable -> {
				throwable.printStackTrace();
				log.error(throwable.getMessage());
				return null;
			});
	}

	/**
	 * 统一处理异步结果
	 * @param futures
	 * @return
	 */
	public static CompletableFuture allOf(CompletableFuture... futures) {
		return allOf(TIMEOUT_VALUE, TIMEOUT_UNIT, futures);
	}

	/**
	 * 统一处理异步结果 - 可设置超时时间
	 * @param futures
	 * @return
	 */
	public static CompletableFuture allOf(long timeout, TimeUnit unit, CompletableFuture... futures) {
		return CompletableFuture.allOf(futures)
			.applyToEither(timeoutAfter(timeout, unit), Function.identity())
			.exceptionally(throwable -> {
				throwable.printStackTrace();
				log.error(throwable.getMessage());
				return null;
			});
	}

	/**
	 * 异步超时处理
	 * @param timeout
	 * @param unit
	 * @param <T>
	 * @return
	 */
	public static <T> CompletableFuture<T> timeoutAfter(long timeout, TimeUnit unit) {
		CompletableFuture<T> result = new CompletableFuture<T>();
		// timeout 时间后 抛出TimeoutException 类似于sentinel / watcher
		Delayer.delayer.schedule(() -> result.completeExceptionally(new TimeoutException()), timeout, unit);
		return result;
	}

	public static <T> CompletableFuture<T> timeoutAfter() {
		CompletableFuture<T> result = new CompletableFuture<T>();
		// timeout 时间后 抛出TimeoutException 类似于sentinel / watcher
		Delayer.delayer.schedule(() -> result.completeExceptionally(new TimeoutException()), TIMEOUT_VALUE,
				TIMEOUT_UNIT);
		return result;
	}

}