package com.lucky.shop.mobile.product.api.factory;

import com.lucky.shop.mobile.product.api.RemoteShopGoodsSkuService;
import com.lucky.shop.mobile.product.api.domain.ShopGoodsSku;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 商品SKU服务降级处理
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 14:31
 */
@Component
@Slf4j
public class ShopGoodsSkuServiceFactory implements FallbackFactory<RemoteShopGoodsSkuService> {
    @Override
    public RemoteShopGoodsSkuService create(Throwable throwable) {
        log.error("商品SKU服务调用失败:{}", throwable.getMessage());
        return new RemoteShopGoodsSkuService() {
            @Override
            public ShopGoodsSku getOne(Long id) {
                return null;
            }
        };
    }
}
