package com.lucky.shop.admin.system.api.factory;

import com.lucky.shop.admin.system.api.RemoteSysCfgService;
import com.lucky.shop.admin.system.api.domain.SysCfg;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 系统参数服务降级处理
 *
 * @Author Guo Huaijian
 * @Date 2020/6/5 20:12
 */
@Component
@Slf4j
public class SysCfgServiceFactory implements FallbackFactory<RemoteSysCfgService> {

    @Override
    public RemoteSysCfgService create(Throwable throwable) {
        log.error("系统参数服务调用失败:{}"+throwable.getMessage());
        return new RemoteSysCfgService() {
            @Override
            public String getCfgValue(String cfgName) {
                return null;
            }

            @Override
            public SysCfg getByCfgName(String cfgName) {
                return null;
            }

            @Override
            public void saveOrUpdate(SysCfg cfg) {

            }
        };
    }
}
