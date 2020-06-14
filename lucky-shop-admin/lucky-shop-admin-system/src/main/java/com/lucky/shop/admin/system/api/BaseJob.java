package com.lucky.shop.admin.system.api;

import com.lucky.shop.admin.system.domain.vo.QuartzJob;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * @Author Guo Huaijian
 * @Date 2020/6/15 0:07
 */
@Component
public class BaseJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap data = context.getJobDetail().getJobDataMap();
        QuartzJob job = (QuartzJob) data.get("job");
        try {
            TaskUtils.executeJob(job);
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }
}
