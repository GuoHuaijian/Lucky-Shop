package com.lucky.shop.admin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.system.api.JobService;
import com.lucky.shop.admin.system.domain.SysTask;
import com.lucky.shop.admin.system.domain.SysTaskLog;
import com.lucky.shop.admin.system.domain.vo.QuartzJob;
import com.lucky.shop.admin.system.mapper.SysTaskMapper;
import com.lucky.shop.admin.system.service.SysTaskLogService;
import com.lucky.shop.admin.system.service.SysTaskService;
import com.lucky.shop.common.core.factory.PageFactory;
import com.lucky.shop.common.core.tool.LogObjectHolder;
import com.lucky.shop.common.core.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 定时任务
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 23:34
 */
@Slf4j
@Service
public class SysTaskServiceImpl extends ServiceImpl<SysTaskMapper, SysTask> implements SysTaskService {

    @Autowired
    private JobService jobService;

    @Autowired
    private SysTaskLogService taskLogService;

    /**
     * 获取定时任务管理列表
     *
     * @param name
     * @return
     */
    @Override
    public List<SysTask> list(String name) {
        if (StringUtil.isNullOrEmpty(name)) {
            return this.list();
        } else {
            QueryWrapper<SysTask> wrapper = new QueryWrapper<>();
            wrapper.like(SysTask.COL_NAME, name);
            return this.list(wrapper);
        }
    }

    /**
     * 新增定时任务管理
     *
     * @param task
     */
    @Override
    public void add(SysTask task) {
        if (task.getId() == null) {
            this.save(task);
        } else {
            SysTask old = this.getById(task.getId());
            LogObjectHolder.me().set(old);
            old.setName(task.getName());
            old.setCron(task.getCron());
            old.setJobClass(task.getJobClass());
            old.setNote(task.getNote());
            old.setData(task.getData());
            this.updateById(old);
        }
    }

    /**
     * 删除定时任务管理
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        this.removeById(id);
    }

    /**
     * 禁用定时任务
     *
     * @param taskId
     */
    @Override
    public void disable(Long taskId) {
        SysTask task = getById(taskId);
        task.setDisabled(true);
        this.save(task);
        log.info("禁用定时任务:{}", taskId.toString());
        try {
            QuartzJob job = jobService.getJob(task.getId().toString(), task.getJobGroup());
            if (job != null) {
                jobService.deleteJob(job);
            }
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 启用定时任务
     *
     * @param taskId
     */
    @Override
    public void enable(Long taskId) {
        SysTask task = getById(taskId);
        task.setDisabled(false);
        this.save(task);
        log.info("启用定时任务{}", taskId.toString());
        try {
            QuartzJob job = jobService.getJob(task.getId().toString(), task.getJobGroup());
            if (job != null) {
                jobService.deleteJob(job);
            }
            if (!task.getDisabled()) {
                jobService.addJob(jobService.getJob(task));
            }
        } catch (SchedulerException e) {
            throw new RuntimeException();
        }
    }

    /**
     * 定时任务日志
     *
     * @param taskId
     * @return
     */
    @Override
    public Page<SysTaskLog> logList(Long taskId) {
        Page<SysTaskLog> page = new PageFactory<SysTaskLog>().defaultPage();
        QueryWrapper<SysTaskLog> wrapper = new QueryWrapper<>();
        wrapper.eq(SysTaskLog.COL_ID_TASK, taskId);
        IPage<SysTaskLog> taskLogPage = taskLogService.page(page, wrapper);
        return (Page<SysTaskLog>) taskLogPage;
    }
}
