package com.itjing.config;

import com.itjing.job.DateTimeJob;
import com.itjing.job.DateTimeJob2;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lijing
 * @date 2021年11月17日 16:36
 * @description Quartz配置类
 */
@Configuration
public class QuartzConfig {

    // -----------------------Job1-----------------------------
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
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 12 * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(printTimeJobDetail()) // 关联上述的JobDetail
                .withIdentity("quartzTaskService") // 给Trigger起个名字
                .withSchedule(cronScheduleBuilder)
                .build();
    }


    // -----------------------Job2-----------------------------
    @Bean
    public JobDetail printTimeJob2Detail() {
        return JobBuilder.newJob(DateTimeJob2.class) // PrintTimeJob我们的业务类
                .withIdentity("DateTimeJob2") // 可以给该JobDetail起一个id
                // 每个JobDetail内都有一个Map，包含了关联到这个Job的数据，在Job类中可以通过context获取
                .usingJobData("msg", "Hello Quartz") // 关联键值对
                .storeDurably() // 即使没有Trigger关联时，也不需要删除该JobDetail
                .build();
    }

    @Bean
    public Trigger printTimeJob2Trigger() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 12 * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(printTimeJob2Detail()) // 关联上述的JobDetail
                .withIdentity("quartzTaskService2") // 给Trigger起个名字
                .withSchedule(cronScheduleBuilder)
                .build();
    }
}
