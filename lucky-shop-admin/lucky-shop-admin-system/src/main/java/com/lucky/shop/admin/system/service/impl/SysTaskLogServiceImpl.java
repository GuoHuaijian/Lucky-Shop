package com.lucky.shop.admin.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.system.domain.SysTaskLog;
import com.lucky.shop.admin.system.mapper.SysTaskLogMapper;
import com.lucky.shop.admin.system.service.SysTaskLogService;
import org.springframework.stereotype.Service;

/**
 * 定时任务日志
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 23:34
 */
@Service
public class SysTaskLogServiceImpl extends ServiceImpl<SysTaskLogMapper, SysTaskLog> implements SysTaskLogService {

}
