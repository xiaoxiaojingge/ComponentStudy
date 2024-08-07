package com.itjing.quartz.config;

import com.itjing.quartz.job.DateTimeJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: lijing
 * @Date: 2021年05月26日 10:32
 * @Description:
 */
@Configuration
public class QuartzConfig {

	@Bean
	public JobDetail printTimeJobDetail() {
		return JobBuilder.newJob(DateTimeJob.class) // PrintTimeJob我们的业务类
			.withIdentity("DateTimeJob") // 可以给该JobDetail起一个id
			// 每个JobDetail内都有一个Map，包含了关联到这个Job的数据，在Job类中可以通过context获取
			.usingJobData("msg", "Hello Quartz") // 关联键值对
			.storeDurably() // 即使没有Trigger关联时，也不需要删除该JobDetail
			.build();
	}

	@Bean
	public Trigger printTimeJobTrigger() {
		CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/1 * * * * ?");
		return TriggerBuilder.newTrigger()
			.forJob(printTimeJobDetail()) // 关联上述的JobDetail
			.withIdentity("quartzTaskService") // 给Trigger起个名字
			.withSchedule(cronScheduleBuilder)
			.build();
	}

}
