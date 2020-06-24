package com.lucky.shop.mobile.product.api.factory;

import com.lucky.shop.mobile.product.api.RemoteShopGoodsService;
import com.lucky.shop.mobile.product.api.domain.ShopGoods;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 商品服务降级处理
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 14:42
 */
@Component
@Slf4j
public class ShopGoodsServiceFactory implements FallbackFactory<RemoteShopGoodsService> {
    @Override
    public RemoteShopGoodsService create(Throwable throwable) {
        log.error("商品SKU服务调用失败:{}", throwable.getMessage());
        return new RemoteShopGoodsService() {
            @Override
            public ShopGoods getOne(Long id) {
                return null;
            }

            @Override
            public void save(ShopGoods goods) {

            }
        };
    }
}
