package com.lucky.shop.admin.system.api.factory;

import com.lucky.shop.admin.system.api.RemoteSysUserService;
import com.lucky.shop.admin.system.api.domain.SysUser;
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
public class SysUserServiceFactory implements RemoteSysUserService {

    @Override
    public SysUser getUserByAccount(String account) {
        log.error("账号服务调用失败:{}");
        return null;
    }

    @Override
    public SysUser getUserById(String id) {
        log.error("账号服务调用失败:{}");
        return null;
    }
}
