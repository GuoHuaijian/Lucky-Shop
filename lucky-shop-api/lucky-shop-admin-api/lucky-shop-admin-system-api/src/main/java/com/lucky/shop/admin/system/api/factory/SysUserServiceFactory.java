package com.lucky.shop.admin.system.api.factory;

import com.lucky.shop.admin.system.api.RemoteSysUserService;
import com.lucky.shop.admin.system.api.domain.SysUser;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 账号服务降级处理
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 18:24
 */
@Component
@Slf4j
public class SysUserServiceFactory implements FallbackFactory<RemoteSysUserService> {

    @Override
    public RemoteSysUserService create(Throwable throwable) {
        log.error("账号服务调用失败:{}", throwable.getMessage());
        return new RemoteSysUserService() {
            @Override
            public SysUser getUserByAccount(String account) {
                return null;
            }
        };
    }
}
