package com.lucky.shop.mobile.product.api;

import com.lucky.shop.common.core.constant.ServiceNameConstants;
import com.lucky.shop.config.configuration.FeignRequestConfiguration;
import com.lucky.shop.mobile.product.api.domain.ShopGoodsSku;
import com.lucky.shop.mobile.product.api.factory.ShopGoodsSkuServiceFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 商品SKU服务接口
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 14:27
 */
@FeignClient(value = ServiceNameConstants.LUCKY_SHOP_MOBILE_PRODUCT, path = "goods/sku", configuration =
        FeignRequestConfiguration.class, fallbackFactory = ShopGoodsSkuServiceFactory.class)
public interface RemoteShopGoodsSkuService {

    /**
     * 根据id获取商品SKU
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    ShopGoodsSku getOne(@PathVariable Long id);
}
