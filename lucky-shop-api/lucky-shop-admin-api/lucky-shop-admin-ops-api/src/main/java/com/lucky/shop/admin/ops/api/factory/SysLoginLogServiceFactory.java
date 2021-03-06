package com.lucky.shop.admin.ops.api.factory;

import com.lucky.shop.admin.ops.api.RemoteSysLoginLogService;
import com.lucky.shop.admin.ops.api.domain.LoginLog;
import com.lucky.shop.common.core.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 登录日志服务降级处理
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 14:39
 */
@Component
@Slf4j
public class SysLoginLogServiceFactory implements RemoteSysLoginLogService {

    @Override
    public ResponseResult save(LoginLog loginLog) {
        log.error("登录日志服务调用失败:{}");
        return null;
    }
}
