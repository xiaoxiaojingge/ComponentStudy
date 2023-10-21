package com.itjing.task.controller;

import com.alibaba.fastjson2.JSONObject;
import com.itjing.task.config.CronTaskRegistrar;
import com.itjing.task.config.SchedulingRunnable;
import com.itjing.task.constants.JobStatusConstant;
import com.itjing.task.domain.DynamicJob;
import com.itjing.task.mapper.DynamicJobMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * @Description
 * @Author lijing
 * @Date 2023-10-21 14:14
 */
@RestController
@RequestMapping("/dynamic_job")
public class DynamicJobController {

    @Resource
    private DynamicJobMapper dynamicJobMapper;

    @Resource
    private CronTaskRegistrar cronTaskRegistrar;

    /**
     * 添加动态任务
     *
     * @return {@link String}
     */
    @PostMapping("/add")
    @Transactional
    public String addDynamicJob() {
        // 方法参数
        LinkedHashMap<String, Object> methodParams = new LinkedHashMap<>();
        methodParams.put("businessId", Integer.valueOf(1000));
        DynamicJob dynamicJob = new DynamicJob();
        dynamicJob.setBusinessId(1000);
        dynamicJob.setBeanName("testService");
        dynamicJob.setMethodName("printInfo");
        dynamicJob.setMethodParams(JSONObject.toJSONString(methodParams));
        dynamicJob.setRemark("测试动态任务");
        dynamicJob.setCronExpression("0 31 15 * * ?");
        dynamicJob.setCreateUser("lijing");
        dynamicJob.setJobStatus(JobStatusConstant.NORMAL);
        int insertCount = dynamicJobMapper.insert(dynamicJob);
        if (insertCount == 0) {
            return "insert failed!";
        } else {
            if (Objects.equals(dynamicJob.getJobStatus(), JobStatusConstant.NORMAL)) {
                SchedulingRunnable task = new SchedulingRunnable(dynamicJob.getBeanName(), dynamicJob.getMethodName(), methodParams);
                cronTaskRegistrar.addCronTask(task, dynamicJob.getCronExpression());
            }
        }
        return "success!";
    }

    /**
     * 修改动态任务
     *
     * @return {@link String}
     */
    @PostMapping("/update")
    @Transactional
    public String updateDynamicJob() {
        DynamicJob existedDynamicJob = dynamicJobMapper.selectByBusinessId(999);
        if (Objects.nonNull(existedDynamicJob)) {

            // 方法参数
            LinkedHashMap<String, Object> methodParams = new LinkedHashMap<>();
            methodParams.put("businessId", Integer.valueOf(999));
            DynamicJob dynamicJob = new DynamicJob();
            dynamicJob.setJobId(existedDynamicJob.getJobId());
            dynamicJob.setBusinessId(999);
            dynamicJob.setBeanName("testService");
            dynamicJob.setMethodName("printInfo");
            dynamicJob.setMethodParams(JSONObject.toJSONString(methodParams));
            dynamicJob.setRemark("测试动态任务");
            dynamicJob.setCronExpression("0 45 16 * * ?");
            dynamicJob.setCreateUser("jinggege");
            dynamicJob.setJobStatus(JobStatusConstant.NORMAL);

            int updateCount = dynamicJobMapper.updateByPrimaryKey(dynamicJob);
            if (updateCount == 0) {
                return "update failed!";
            } else {
                // 先移除再添加
                if (existedDynamicJob.getJobStatus().equals(JobStatusConstant.NORMAL)) {
                    LinkedHashMap existedMethodParams = JSONObject.parseObject(existedDynamicJob.getMethodParams(), LinkedHashMap.class);
                    SchedulingRunnable task = new SchedulingRunnable(existedDynamicJob.getBeanName(), existedDynamicJob.getMethodName(), existedMethodParams);
                    cronTaskRegistrar.removeCronTask(task);
                }

                if (Objects.equals(dynamicJob.getJobStatus(), JobStatusConstant.NORMAL)) {
                    SchedulingRunnable task = new SchedulingRunnable(dynamicJob.getBeanName(), dynamicJob.getMethodName(), methodParams);
                    cronTaskRegistrar.addCronTask(task, dynamicJob.getCronExpression());
                }
            }
        } else {
            return "dynamic job not exist!";
        }
        return "success!";
    }
}
