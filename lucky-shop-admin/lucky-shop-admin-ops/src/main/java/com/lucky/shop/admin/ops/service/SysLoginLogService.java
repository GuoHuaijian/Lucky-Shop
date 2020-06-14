package com.lucky.shop.admin.ops.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.admin.ops.domain.SysLoginLog;

/**
 * 登录日志
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 13:10
 */
public interface SysLoginLogService extends IService<SysLoginLog> {

    /**
     * 登录日志列表
     *
     * @param beginTime
     * @param endTime
     * @param logName
     * @return
     */
    Page<SysLoginLog> list(String beginTime, String endTime, String logName);

    /**
     * 插入日志
     *
     * @param loginLog
     */
    void insert(SysLoginLog loginLog);

    /**
     * 清空日志
     */
    void clear();
}
