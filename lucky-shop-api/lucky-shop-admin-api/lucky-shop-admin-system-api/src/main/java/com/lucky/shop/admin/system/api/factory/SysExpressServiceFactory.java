package com.lucky.shop.admin.system.api.factory;

import com.lucky.shop.admin.system.api.RemoteSysExpressService;
import com.lucky.shop.admin.system.api.domain.SysExpress;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 快递服务降级处理
 *
 * @Author Guo Huaijian
 * @Date 2020/7/12 10:33
 */
@Component
@Slf4j
public class SysExpressServiceFactory implements FallbackFactory<RemoteSysExpressService> {
    @Override
    public RemoteSysExpressService create(Throwable throwable) {
        log.error("快递服务调用失败:{}"+throwable.getMessage());
        return new RemoteSysExpressService() {
            @Override
            public SysExpress getExpressById(Long id) {
                return null;
            }
        };
    }
}
