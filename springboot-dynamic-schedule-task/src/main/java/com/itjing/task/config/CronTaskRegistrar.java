package com.itjing.task.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 定时任务注册类，用来增加、删除定时任务
 * @Author lijing
 * @Date 2023-10-21 13:13
 */
@Component
public class CronTaskRegistrar implements DisposableBean {

	/**
	 * 定时任务
	 */
	private final Map<Runnable, ScheduledTask> SCHEDULED_TASKS = new ConcurrentHashMap<>(16);

	@Resource
	private TaskScheduler taskScheduler;

	public TaskScheduler getScheduler() {
		return this.taskScheduler;
	}

	/**
	 * 添加定时任务
	 * @param task
	 * @param cronExpression
	 */
	public void addCronTask(Runnable task, String cronExpression) {
		addCronTask(new CronTask(task, cronExpression));
	}

	public void addCronTask(CronTask cronTask) {
		if (cronTask != null) {
			Runnable task = cronTask.getRunnable();
			if (SCHEDULED_TASKS.containsKey(task)) {
				removeCronTask(task);
			}
			SCHEDULED_TASKS.put(task, scheduleCronTask(cronTask));
		}
	}

	/**
	 * 移除定时任务
	 * @param task
	 */
	public void removeCronTask(Runnable task) {
		ScheduledTask scheduledTask = SCHEDULED_TASKS.remove(task);
		if (scheduledTask != null) {
			scheduledTask.cancel();
		}
	}

	public ScheduledTask scheduleCronTask(CronTask cronTask) {
		ScheduledTask scheduledTask = new ScheduledTask();
		scheduledTask.future = this.taskScheduler.schedule(cronTask.getRunnable(), cronTask.getTrigger());
		return scheduledTask;
	}

	/**
	 * 停止服务自动执行清除逻辑
	 */
	@Override
	public void destroy() {
		for (ScheduledTask task : SCHEDULED_TASKS.values()) {
			task.cancel();
		}
		SCHEDULED_TASKS.clear();
	}

}