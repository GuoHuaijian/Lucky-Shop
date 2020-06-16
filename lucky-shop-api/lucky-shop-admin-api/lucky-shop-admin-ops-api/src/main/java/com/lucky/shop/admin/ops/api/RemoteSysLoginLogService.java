package com.lucky.shop.admin.ops.api;

import com.lucky.shop.admin.ops.api.domain.LoginLog;
import com.lucky.shop.admin.ops.api.factory.SysLoginLogServiceFactory;
import com.lucky.shop.common.core.constant.ServiceNameConstants;
import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.config.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 登录日志服务接口
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 14:35
 */
@FeignClient(value = ServiceNameConstants.LUCKY_SHOP_ADMIN_OPS, path = "ops/loginLog", configuration =
        FeignRequestConfiguration.class, fallbackFactory = SysLoginLogServiceFactory.class)
public interface RemoteSysLoginLogService {

    /**
     * 插入日志
     *
     * @param loginLog
     * @return
     */
    @PostMapping()
    ResponseResult save(LoginLog loginLog);
}
