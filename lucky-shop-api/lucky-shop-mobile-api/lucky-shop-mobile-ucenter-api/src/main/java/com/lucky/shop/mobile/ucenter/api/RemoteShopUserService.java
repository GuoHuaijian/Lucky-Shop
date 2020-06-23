package com.lucky.shop.mobile.ucenter.api;

import com.lucky.shop.common.core.constant.ServiceNameConstants;
import com.lucky.shop.config.configuration.FeignRequestConfiguration;
import com.lucky.shop.mobile.ucenter.api.domain.ShopUser;
import com.lucky.shop.mobile.ucenter.api.factory.ShopUserServiceFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 用户服务接口
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 0:53
 */
@FeignClient(value = ServiceNameConstants.LUCKY_SHOP_MOBILE_UCENTER, path = "user/cart", configuration =
        FeignRequestConfiguration.class, fallbackFactory = ShopUserServiceFactory.class)
public interface RemoteShopUserService {

    /**
     * 通过登录账号获取用户
     *
     * @param account
     * @return
     */
    @GetMapping("/account/{account}")
    ShopUser getUserByAccount(@PathVariable String account);
}
