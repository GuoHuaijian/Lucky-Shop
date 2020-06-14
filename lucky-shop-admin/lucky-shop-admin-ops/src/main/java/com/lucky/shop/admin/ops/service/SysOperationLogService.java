package com.lucky.shop.admin.ops.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.admin.ops.domain.SysOperationLog;

import java.util.List;

/**
 * 操作日志
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 13:10
 */
public interface SysOperationLogService extends IService<SysOperationLog> {

    /**
     * 操作日志列表
     *
     * @param beginTime
     * @param endTime
     * @param logName
     * @param logType
     * @return
     */
    Page<SysOperationLog> list(String beginTime, String endTime, String logName, Integer logType);

    /**
     * 查询指定用户的操作日志列表
     *
     * @return
     */
    List<SysOperationLog> OperationLogList();

    /**
     * 插入日志
     *
     * @param operationLog
     */
    void insert(SysOperationLog operationLog);

    /**
     * 清空日志
     */
    void clear();

}
