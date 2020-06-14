package com.lucky.shop.admin.system.api;

import com.lucky.shop.admin.system.domain.SysTask;
import com.lucky.shop.admin.system.domain.SysTaskLog;
import com.lucky.shop.admin.system.domain.vo.QuartzJob;
import com.lucky.shop.admin.system.service.SysTaskLogService;
import com.lucky.shop.admin.system.service.SysTaskService;
import com.lucky.shop.common.core.utils.StringUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @Author Guo Huaijian
 * @Date 2020/6/15 0:02
 */
@Component
public abstract class JobExecuter {

    protected static final Logger log = LoggerFactory.getLogger(JobExecuter.class);

    @Autowired
    private SysTaskService taskService;

    @Autowired
    private SysTaskLogService taskLogService;

    private QuartzJob job;

    public void setJob(QuartzJob job) {
        this.job = job;
    }

    public void execute() {
        Map dataMap = job.getDataMap();
        String taskId = job.getJobName();
        SysTask task = taskService.getById(Long.valueOf(taskId));
        final String taskName = task.getName();
        log.info(">>>>>>>>>>>>>>>>>开始执行定时任务[" + taskName + "]...<<<<<<<<<<<<<<<<<<<");

        String exeResult = "执行成功";
        final SysTaskLog taskLog = new SysTaskLog();
        taskLog.setName(taskName);
        final Date exeAt = new Date();
        taskLog.setExecAt(exeAt);
        taskLog.setIdTask(task.getId());
        //默认是成功 出异常后改成失败
        taskLog.setExecSuccess(SysTaskLog.EXE_SUCCESS_RESULT);
        try {
            execute(dataMap);
            task.setExecResult(exeResult);
        } catch (Exception e) {
            task.setExecResult("执行失败");
            log.error("exeucte " + getClass().getName() + " error : ", e);
            exeResult = "执行失败\n";
            exeResult += ExceptionUtils.getStackTrace(e);
            taskLog.setExecSuccess(SysTaskLog.EXE_FAILURE_RESULT);
            taskLog.setJobException(exeResult);
        }

        task.setExecAt(exeAt);
        taskLogService.save(taskLog);
        taskService.save(task);
        log.info(">>>>>>>>>>>>>>>>>执行定时任务[" + taskName + "]结束<<<<<<<<<<<<<<<<<<<");
    }

    /**
     * @param dataMap 数据库配置的参数
     */
    public abstract void execute(Map<String, Object> dataMap) throws Exception;

    public String getEmail() {
        return getEmail("snowalert@xuezhongdai.cn");
    }

    public String getEmail(String defaultEmail) {
        Map<String, Object> dataMap = job.getDataMap();
        String toEmail = null;
        if (dataMap != null && dataMap.containsKey("email")) {
            toEmail = StringUtil.sNull(dataMap.get("email"));
        }
        if (StringUtil.isEmpty(toEmail)) {
            toEmail = defaultEmail;
        }
        return toEmail;
    }

}
