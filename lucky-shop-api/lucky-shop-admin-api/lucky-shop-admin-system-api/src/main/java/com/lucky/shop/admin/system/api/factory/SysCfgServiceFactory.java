package com.lucky.shop.admin.system.api.factory;

import com.lucky.shop.admin.system.api.SysCfgServiceApi;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 系统参数服务降级处理
 * @Author Guo Huaijian
 * @Date 2020/6/5 20:12
 */
@Component
@Slf4j
public class SysCfgServiceFactory implements FallbackFactory<SysCfgServiceApi> {
    @Override
    public SysCfgServiceApi create(Throwable throwable) {
        log.error("系统参数服务调用失败:{}", throwable.getMessage());
        return new SysCfgServiceApi() {
            @Override
            public String getCfgValue(String cfgName) {
                return null;
            }
        };
    }
}
