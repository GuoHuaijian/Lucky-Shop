package com.lucky.shop.mobile.ucenter.api;

import com.lucky.shop.common.core.constant.ServiceNameConstants;
import com.lucky.shop.config.configuration.FeignRequestConfiguration;
import com.lucky.shop.mobile.ucenter.api.domain.ShopAddress;
import com.lucky.shop.mobile.ucenter.api.factory.ShopAddressServiceFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 地址服务接口
 *
 * @Author Guo Huaijian
 * @Date 2020/6/23 23:50
 */
@FeignClient(value = ServiceNameConstants.LUCKY_SHOP_MOBILE_UCENTER, path = "user/address", configuration =
        FeignRequestConfiguration.class, fallbackFactory = ShopAddressServiceFactory.class)
public interface RemoteShopAddressService {

    /**
     * 获取用户默认收货地址
     *
     * @param idUser
     * @return
     */
    @GetMapping("/{idUser}")
    ShopAddress getDefaultAddr(@PathVariable Long idUser);

    /**
     * 根据主键获取地址
     *
     * @param chosenAddressId
     * @return
     */
    @GetMapping("/{chosenAddressId}")
    ShopAddress get(@PathVariable Long chosenAddressId);
}
