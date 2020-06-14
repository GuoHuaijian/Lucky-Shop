package com.lucky.shop.admin.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.admin.system.domain.SysTask;
import com.lucky.shop.admin.system.domain.SysTaskLog;

import java.util.List;

/**
 * 定时任务
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 23:34
 */
public interface SysTaskService extends IService<SysTask> {

    /**
     * 获取定时任务管理列表
     *
     * @param name
     * @return
     */
    List<SysTask> list(String name);

    /**
     * 新增定时任务管理
     *
     * @param task
     */
    void add(SysTask task);

    /**
     * 删除定时任务管理
     *
     * @param id
     * @return
     */
    void delete(Long id);

    /**
     * 禁用定时任务
     *
     * @param taskId
     */
    void disable(Long taskId);

    /**
     * 启用定时任务
     *
     * @param taskId
     */
    void enable(Long taskId);

    /**
     * 定时任务日志
     *
     * @param taskId
     * @return
     */
    Page<SysTaskLog> logList(Long taskId);

}
