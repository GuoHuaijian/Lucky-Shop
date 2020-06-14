package com.lucky.shop.admin.ops.api;

import com.lucky.shop.admin.ops.api.domain.OperationLog;
import com.lucky.shop.admin.ops.api.factory.SysOperationLogServiceFactory;
import com.lucky.shop.common.core.constant.ServiceNameConstants;
import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.config.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 操作日志服务接口
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 14:36
 */
@FeignClient(value = ServiceNameConstants.LUCKY_SHOP_ADMIN_OPS, path = "log", configuration =
        FeignRequestConfiguration.class, fallbackFactory = SysOperationLogServiceFactory.class)
public interface RemoteSysOperationLogService {

    /**
     * 插入日志
     *
     * @param operationLog
     * @return
     */
    @PostMapping()
    ResponseResult save(OperationLog operationLog);
}
