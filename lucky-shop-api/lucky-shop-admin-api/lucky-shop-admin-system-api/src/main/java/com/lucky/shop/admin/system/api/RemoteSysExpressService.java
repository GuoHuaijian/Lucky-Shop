package com.lucky.shop.admin.system.api;

import com.lucky.shop.admin.system.api.domain.SysExpress;
import com.lucky.shop.admin.system.api.factory.SysExpressServiceFactory;
import com.lucky.shop.common.core.constant.ServiceNameConstants;
import com.lucky.shop.config.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 快递服务接口
 *
 * @Author Guo Huaijian
 * @Date 2020/7/12 10:29
 */
@FeignClient(value = ServiceNameConstants.LUCKY_SHOP_ADMIN_SYSTEM, path = "system/sys/express", configuration =
        FeignRequestConfiguration.class, fallbackFactory = SysExpressServiceFactory.class)
public interface RemoteSysExpressService {

    /**
     * 通过id查询快递
     *
     * @param id
     * @return
     */
    @GetMapping("test/{id}")
    SysExpress getExpressById(@PathVariable Long id);
}
