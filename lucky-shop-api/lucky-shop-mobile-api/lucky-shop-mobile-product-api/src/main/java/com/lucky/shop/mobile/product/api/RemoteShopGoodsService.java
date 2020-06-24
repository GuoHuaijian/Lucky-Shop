package com.lucky.shop.mobile.product.api;

import com.lucky.shop.common.core.constant.ServiceNameConstants;
import com.lucky.shop.config.configuration.FeignRequestConfiguration;
import com.lucky.shop.mobile.product.api.domain.ShopGoods;
import com.lucky.shop.mobile.product.api.factory.ShopGoodsServiceFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 商品服务接口
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 14:38
 */
@FeignClient(value = ServiceNameConstants.LUCKY_SHOP_MOBILE_PRODUCT, path = "goods", configuration =
        FeignRequestConfiguration.class, fallbackFactory = ShopGoodsServiceFactory.class)
public interface RemoteShopGoodsService {

    /**
     * 根据主键获取商品
     *
     * @param id
     * @return
     */
    @GetMapping("getOne/{id}")
    ShopGoods getOne(@PathVariable Long id);

    /**
     * 保存商品
     *
     * @param goods
     */
    @PostMapping()
    void save(@RequestBody ShopGoods goods);
}
