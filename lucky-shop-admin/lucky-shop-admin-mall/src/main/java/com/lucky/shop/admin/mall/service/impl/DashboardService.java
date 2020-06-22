package com.lucky.shop.admin.mall.service.impl;

import com.lucky.shop.admin.mall.service.ShopCartService;
import com.lucky.shop.admin.mall.service.ShopOrderService;
import com.lucky.shop.admin.mall.service.ShopUserService;
import com.lucky.shop.common.core.tool.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 首页
 *
 * @Author Guo Huaijian
 * @Date 2020/6/22 14:49
 */
@Service
public class DashboardService {

    @Autowired
    private ShopOrderService orderService;

    @Autowired
    private ShopUserService shopUserService;

    @Autowired
    private ShopCartService cartService;

    /**
     * 获取首页内容
     *
     * @return
     */
    public Map getDashboardData(){
        long orderCount = orderService.count();
        long userCount = shopUserService.count();
        long cartCount = cartService.count();
        Map orderSumPrice = orderService.getRealPrice();
        Map result = Maps.newHashMap(
                "orderCount",orderCount,
                "userCount",userCount,
                "cartCount",cartCount,
                "orderSumPrice", orderSumPrice!=null?(Double.valueOf(orderSumPrice.get("realPrice").toString())/100):"0"
        );
        return result;
    }
}
