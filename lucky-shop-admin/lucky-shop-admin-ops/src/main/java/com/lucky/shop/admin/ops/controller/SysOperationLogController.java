package com.lucky.shop.admin.ops.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.shop.admin.ops.domain.SysOperationLog;
import com.lucky.shop.admin.ops.service.SysOperationLogService;
import com.lucky.shop.common.core.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 操作日志
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 13:13
 */
@RestController
@RequestMapping("ops/log")
public class SysOperationLogController {

    @Autowired
    private SysOperationLogService operationLogService;

    /**
     * 操作日志列表
     *
     * @param beginTime
     * @param endTime
     * @param logName
     * @param logType
     * @return
     */
    @GetMapping("/list")
//    @RequiresPermissions(value = {Permission.LOG})
    public ResponseResult list(@RequestParam(required = false) String beginTime,
                               @RequestParam(required = false) String endTime,
                               @RequestParam(required = false) String logName,
                               @RequestParam(required = false) Integer logType) {
        Page<SysOperationLog> page = operationLogService.list(beginTime, endTime, logName, logType);
        return ResponseResult.success(page);
    }

    /**
     * 查询指定用户的操作日志列表
     *
     * @return
     */
    @GetMapping("/queryByUser")
//    @RequiresPermissions(value = {Permission.LOG})
    public ResponseResult list() {
        List<SysOperationLog> sysOperationLogs = operationLogService.OperationLogList();
        return ResponseResult.success(sysOperationLogs);
    }

    /**
     * 插入日志
     *
     * @param operationLog
     * @return
     */
    @PostMapping()
//    @RequiresPermissions(value = {Permission.LOG_CLEAR})
    public ResponseResult save(SysOperationLog operationLog) {
        operationLogService.insert(operationLog);
        return ResponseResult.success();
    }

    /**
     * 清空日志
     *
     * @return
     */
    @DeleteMapping()
//    @RequiresPermissions(value = {Permission.LOG_CLEAR})
    public ResponseResult delLog() {
        operationLogService.clear();
        return ResponseResult.success();
    }
}
