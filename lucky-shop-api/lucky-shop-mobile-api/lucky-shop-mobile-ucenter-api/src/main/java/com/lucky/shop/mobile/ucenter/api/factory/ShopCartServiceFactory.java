package com.lucky.shop.mobile.ucenter.api.factory;

import com.lucky.shop.mobile.ucenter.api.RemoteShopCartService;
import com.lucky.shop.mobile.ucenter.api.domain.ShopCart;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 订单服务降级处理
 *
 * @Author Guo Huaijian
 * @Date 2020/6/23 23:28
 */
@Component
@Slf4j
public class ShopCartServiceFactory implements FallbackFactory<RemoteShopCartService> {
    @Override
    public RemoteShopCartService create(Throwable throwable) {
        log.error("订单服务调用失败:{}", throwable.getMessage());
        return new RemoteShopCartService() {
            @Override
            public List<ShopCart> queryAll(Long idUser) {
                return null;
            }

            @Override
            public void deleteAll(List<Long> id) {

            }
        };
    }
}
