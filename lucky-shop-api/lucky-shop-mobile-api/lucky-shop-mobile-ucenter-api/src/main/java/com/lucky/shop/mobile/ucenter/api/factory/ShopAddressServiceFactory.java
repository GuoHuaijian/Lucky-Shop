package com.lucky.shop.mobile.ucenter.api.factory;

import com.lucky.shop.mobile.ucenter.api.RemoteShopAddressService;
import com.lucky.shop.mobile.ucenter.api.domain.ShopAddress;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 地址服务降级处理
 *
 * @Author Guo Huaijian
 * @Date 2020/6/23 23:56
 */
@Component
@Slf4j
public class ShopAddressServiceFactory implements FallbackFactory<RemoteShopAddressService> {
    @Override
    public RemoteShopAddressService create(Throwable throwable) {
        log.error("地址服务调用失败:{}", throwable.getMessage());
        return new RemoteShopAddressService() {
            @Override
            public ShopAddress getDefaultAddr(Long idUser) {
                return null;
            }

            @Override
            public ShopAddress get(Long chosenAddressId) {
                return null;
            }
        };
    }
}
