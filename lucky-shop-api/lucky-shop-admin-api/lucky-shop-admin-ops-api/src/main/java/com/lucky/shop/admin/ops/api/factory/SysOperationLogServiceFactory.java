package com.lucky.shop.admin.ops.api.factory;

import com.lucky.shop.admin.ops.api.RemoteSysOperationLogService;
import com.lucky.shop.admin.ops.api.domain.OperationLog;
import com.lucky.shop.common.core.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 操作日志服务降级处理
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 14:38
 */
@Component
@Slf4j
public class SysOperationLogServiceFactory implements RemoteSysOperationLogService {

    @Override
    public ResponseResult save(OperationLog operationLog) {
        log.error("操作日志服务调用失败:{}");
        return null;
    }
}
