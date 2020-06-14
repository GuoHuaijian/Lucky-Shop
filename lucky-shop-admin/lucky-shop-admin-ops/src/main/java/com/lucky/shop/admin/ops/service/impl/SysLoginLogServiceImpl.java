package com.lucky.shop.admin.ops.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.ops.domain.SysLoginLog;
import com.lucky.shop.admin.ops.mapper.SysLoginLogMapper;
import com.lucky.shop.admin.ops.service.SysLoginLogService;
import com.lucky.shop.common.core.factory.PageFactory;
import com.lucky.shop.common.core.utils.BeanUtil;
import com.lucky.shop.common.core.utils.DateUtil;
import com.lucky.shop.common.core.utils.StringUtil;
import com.lucky.shop.common.core.warpper.LogWarpper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 登录日志
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 13:10
 */
@Service
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLog> implements SysLoginLogService {

    /**
     * 登录日志列表
     *
     * @param beginTime
     * @param endTime
     * @param logName
     * @return
     */
    @Override
    public Page<SysLoginLog> list(String beginTime, String endTime, String logName) {
        Page<SysLoginLog> page = new PageFactory<SysLoginLog>().defaultPage();
        QueryWrapper<SysLoginLog> wrapper = new QueryWrapper<>();
        if (StringUtil.isNotEmpty(beginTime)) {
            wrapper.ge(SysLoginLog.COL_CREATE_TIME, DateUtil.parseDate(beginTime));
        }
        if (StringUtil.isNotEmpty(endTime)) {
            wrapper.le(SysLoginLog.COL_CREATE_TIME, DateUtil.parseDate(endTime));
        }
        if (StringUtil.isNotEmpty(logName)) {
            wrapper.like(SysLoginLog.COL_LOGNAME, logName);
        }
        IPage<SysLoginLog> pageResult = this.page(page, wrapper);
        pageResult.setRecords((List<SysLoginLog>) new LogWarpper(BeanUtil.objectsToMaps(pageResult.getRecords())).warp());
        return (Page<SysLoginLog>) pageResult;
    }

    /**
     * 插入日志
     *
     * @param loginLog
     */
    @Override
    public void insert(SysLoginLog loginLog) {
        this.save(loginLog);
    }

    /**
     * 清空日志
     */
    @Override
    public void clear() {
        QueryWrapper<SysLoginLog> wrapper = new QueryWrapper<>();
        wrapper.isNotNull(SysLoginLog.COL_ID);
        this.remove(wrapper);
    }
}
