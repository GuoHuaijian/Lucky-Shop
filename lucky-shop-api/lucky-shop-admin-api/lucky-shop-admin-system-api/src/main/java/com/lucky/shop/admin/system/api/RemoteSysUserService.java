package com.lucky.shop.admin.system.api;

import com.lucky.shop.admin.system.api.domain.SysUser;
import com.lucky.shop.admin.system.api.factory.FileServiceFactory;
import com.lucky.shop.common.core.constant.ServiceNameConstants;
import com.lucky.shop.config.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 账号接口
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 18:20
 */
@FeignClient(value = ServiceNameConstants.LUCKY_SHOP_ADMIN_SYSTEM, path = "system/user", configuration =
        FeignRequestConfiguration.class, fallbackFactory = FileServiceFactory.class)
public interface RemoteSysUserService {

    /**
     * 根据账号获取用户信息
     *
     * @param account
     * @return
     */
    @GetMapping("userInfo/{account}")
    SysUser getUserByAccount(@PathVariable String account);

    /**
     * 根据id获取用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("userInfo/{id}")
    SysUser getUserById(@PathVariable String id);
}
