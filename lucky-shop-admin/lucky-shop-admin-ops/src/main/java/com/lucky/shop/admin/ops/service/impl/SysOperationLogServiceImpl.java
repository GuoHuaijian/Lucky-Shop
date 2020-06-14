package com.lucky.shop.admin.ops.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.ops.domain.SysOperationLog;
import com.lucky.shop.admin.ops.mapper.SysOperationLogMapper;
import com.lucky.shop.admin.ops.service.SysOperationLogService;
import com.lucky.shop.admin.system.api.RemoteSysUserService;
import com.lucky.shop.admin.system.api.domain.SysUser;
import com.lucky.shop.common.core.enums.BizLogType;
import com.lucky.shop.common.core.factory.PageFactory;
import com.lucky.shop.common.core.utils.BeanUtil;
import com.lucky.shop.common.core.utils.DateUtil;
import com.lucky.shop.common.core.utils.StringUtil;
import com.lucky.shop.common.core.warpper.LogWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 操作日志
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 13:10
 */
@Service
public class SysOperationLogServiceImpl extends ServiceImpl<SysOperationLogMapper, SysOperationLog> implements SysOperationLogService {

    @Autowired
    private RemoteSysUserService sysUserService;

    /**
     * 操作日志列表
     *
     * @param beginTime
     * @param endTime
     * @param logName
     * @param logType
     * @return
     */
    @Override
    public Page<SysOperationLog> list(String beginTime, String endTime, String logName, Integer logType) {
        Page<SysOperationLog> page = new PageFactory<SysOperationLog>().defaultPage();
        QueryWrapper<SysOperationLog> wrapper = new QueryWrapper<>();
        if (StringUtil.isNotEmpty(beginTime)) {
            wrapper.ge(SysOperationLog.COL_CREATE_TIME, DateUtil.parseDate(beginTime));
        }
        if (StringUtil.isNotEmpty(endTime)) {
            wrapper.le(SysOperationLog.COL_CREATE_TIME, DateUtil.parseDate(endTime));
        }
        if (StringUtil.isNotEmpty(logName)) {
            wrapper.like(SysOperationLog.COL_LOGNAME, logName);
        }
        if (logType != null) {
            wrapper.eq(SysOperationLog.COL_LOGTYPE, BizLogType.valueOf(logType));
        }
        IPage<SysOperationLog> logIPage = this.page(page, wrapper);
        logIPage.setRecords((List<SysOperationLog>) new LogWarpper(BeanUtil.objectsToMaps(page.getRecords())).warp());
        return (Page<SysOperationLog>) logIPage;
    }

    /**
     * 查询指定用户的操作日志列表
     *
     * @return
     */
    @Override
    public List<SysOperationLog> OperationLogList() {
        Page<SysOperationLog> page = new Page<>();
        QueryWrapper<SysOperationLog> wrapper = new QueryWrapper<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String account = authentication.getName();
        SysUser sysUser = sysUserService.getUserByAccount(account);
        wrapper.eq(SysOperationLog.COL_USERID, sysUser.getId());
        List<SysOperationLog> logList = this.page(page, wrapper).getRecords();
        return logList;
    }

    /**
     * 插入日志
     *
     * @param operationLog
     */
    @Override
    public void insert(SysOperationLog operationLog) {
        this.save(operationLog);
    }

    /**
     * 清空日志
     */
    @Override
    public void clear() {
        QueryWrapper<SysOperationLog> wrapper = new QueryWrapper<>();
        wrapper.isNotNull(SysOperationLog.COL_ID);
        this.remove(wrapper);
    }
}
