package com.lucky.shop.mobile.ucenter.api.factory;

import com.lucky.shop.mobile.ucenter.api.RemoteShopUserService;
import com.lucky.shop.mobile.ucenter.api.domain.ShopUser;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 用户服务降级处理
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 0:56
 */
@Component
@Slf4j
public class ShopUserServiceFactory implements FallbackFactory<RemoteShopUserService> {
    @Override
    public RemoteShopUserService create(Throwable throwable) {
        log.error("订单服务调用失败:{}", throwable.getMessage());
        return new RemoteShopUserService() {
            @Override
            public ShopUser getUserByAccount(String account) {
                return null;
            }
        };
    }
}
