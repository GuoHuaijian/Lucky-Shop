package com.lucky.shop.mobile.ucenter.api;

import com.lucky.shop.common.core.constant.ServiceNameConstants;
import com.lucky.shop.config.configuration.FeignRequestConfiguration;
import com.lucky.shop.mobile.ucenter.api.domain.ShopCart;
import com.lucky.shop.mobile.ucenter.api.factory.ShopCartServiceFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 订单服务接口
 *
 * @Author Guo Huaijian
 * @Date 2020/6/23 23:22
 */
@FeignClient(value = ServiceNameConstants.LUCKY_SHOP_MOBILE_UCENTER, path = "user/cart", configuration =
        FeignRequestConfiguration.class, fallbackFactory = ShopCartServiceFactory.class)
public interface RemoteShopCartService {

    /**
     * 查询用户购物车
     *
     * @param idUser
     * @return
     */
    @GetMapping("/{idUser}")
    List<ShopCart> queryAll(@PathVariable Long idUser);

    /**
     * 删除全部购物车
     *
     * @param id
     */
    @DeleteMapping("/deleteCart")
    void deleteAll(List<Long> id);
}
