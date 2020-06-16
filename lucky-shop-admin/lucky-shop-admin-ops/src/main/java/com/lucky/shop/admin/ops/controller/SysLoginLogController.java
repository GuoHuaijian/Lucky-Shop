package com.lucky.shop.admin.ops.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.shop.admin.ops.domain.SysLoginLog;
import com.lucky.shop.admin.ops.service.SysLoginLogService;
import com.lucky.shop.common.core.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 登录日志
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 13:13
 */
@RestController
@RequestMapping("ops/loginLog")
public class SysLoginLogController {

    @Autowired
    private SysLoginLogService loginLogService;

    /**
     * 登录日志列表
     *
     * @param beginTime
     * @param endTime
     * @param logName
     * @return
     */
    @GetMapping(value = "/list")
//    @RequiresPermissions(value = {Permission.LOGIN_LOG})
    public ResponseResult list(@RequestParam(required = false) String beginTime,
                               @RequestParam(required = false) String endTime,
                               @RequestParam(required = false) String logName) {
        Page<SysLoginLog> pageResult = loginLogService.list(beginTime, endTime, logName);
        return ResponseResult.success(pageResult);

    }

    /**
     * 插入日志
     *
     * @param loginLog
     * @return
     */
    @PostMapping()
    public ResponseResult save(SysLoginLog loginLog) {
        loginLogService.insert(loginLog);
        return ResponseResult.success();
    }

    /**
     * 清空日志
     *
     * @return
     */
    @DeleteMapping()
//    @RequiresPermissions(value = {Permission.LOGIN_LOG_CLEAR})
    public ResponseResult clear() {
        loginLogService.clear();
        return ResponseResult.success();
    }
}
