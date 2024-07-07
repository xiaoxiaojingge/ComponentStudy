package com.itjing.task.config;

import com.alibaba.fastjson2.JSONObject;
import com.itjing.task.constants.JobStatusConstant;
import com.itjing.task.domain.DynamicJob;
import com.itjing.task.mapper.DynamicJobMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Description SpringBoot项目启动完成后，加载数据库里状态为正常的定时任务
 * @Author lijing
 * @Date 2023-10-21 16:46
 */
@Slf4j
@Component
public class DynamicJobRunner implements CommandLineRunner {

	@Resource
	private DynamicJobMapper dynamicJobMapper;

	@Resource
	private CronTaskRegistrar cronTaskRegistrar;

	@Override
	public void run(String... args) {
		// 初始加载数据库里状态为正常的定时任务
		List<DynamicJob> jobList = dynamicJobMapper.listJobsByStatus(JobStatusConstant.NORMAL);
		if (CollectionUtils.isNotEmpty(jobList)) {
			for (DynamicJob job : jobList) {
				LinkedHashMap methodParams = JSONObject.parseObject(job.getMethodParams(), LinkedHashMap.class);
				SchedulingRunnable task = new SchedulingRunnable(job.getBeanName(), job.getMethodName(), methodParams);
				cronTaskRegistrar.addCronTask(task, job.getCronExpression());
			}
			log.info("定时任务已加载完毕...");
		}
	}

}