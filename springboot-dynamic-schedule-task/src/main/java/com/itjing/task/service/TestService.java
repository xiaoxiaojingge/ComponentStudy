package com.itjing.task.service;

import com.itjing.task.config.CronTaskRegistrar;
import com.itjing.task.constants.JobStatusConstant;
import com.itjing.task.domain.DynamicJob;
import com.itjing.task.mapper.DynamicJobMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Description
 * @Author lijing
 * @Date 2023-10-21 14:23
 */
@Service
@Slf4j
public class TestService {

    @Resource
    private DynamicJobMapper dynamicJobMapper;

    @Resource
    private CronTaskRegistrar cronTaskRegistrar;

    public void printInfo(Integer businessId) {

        // 执行具体业务
        log.info("businessId:{}", businessId);

        // 移除动态定时任务信息
        // 可不清除，留作记录
        // 任务执行完成可以修改状态为暂停
        /*DynamicJob dynamicJob = dynamicJobMapper.selectByBusinessId(businessId);
        if (Objects.nonNull(dynamicJob)) {
            int deleteCount = dynamicJobMapper.deleteByPrimaryKey(dynamicJob.getJobId());
            if (deleteCount == 0) {

            } else {
                if (dynamicJob.getJobStatus().equals(JobStatusConstant.NORMAL)) {
                    LinkedHashMap existedMethodParams = JSONObject.parseObject(dynamicJob.getMethodParams(), LinkedHashMap.class);
                    SchedulingRunnable task = new SchedulingRunnable(dynamicJob.getBeanName(), dynamicJob.getMethodName(), existedMethodParams);
                    cronTaskRegistrar.removeCronTask(task);
                }
            }
        }*/
        DynamicJob dynamicJob = dynamicJobMapper.selectByBusinessId(businessId);
        if (Objects.nonNull(dynamicJob)) {
            dynamicJob.setJobStatus(JobStatusConstant.PAUSE);
            dynamicJobMapper.updateByPrimaryKeySelective(dynamicJob);
        }
    }

}
