package com.lucky.shop.admin.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.mall.domain.ShopCart;
import com.lucky.shop.admin.mall.domain.ShopOrder;
import com.lucky.shop.admin.mall.domain.ShopUser;
import com.lucky.shop.admin.mall.mapper.ShopUserMapper;
import com.lucky.shop.admin.mall.service.ShopCartService;
import com.lucky.shop.admin.mall.service.ShopOrderService;
import com.lucky.shop.admin.mall.service.ShopUserService;
import com.lucky.shop.common.core.tool.Maps;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 商城会员ServiceImpl
 *
 * @Create by Guo Huaijian
 * @Since 2020/6/2 20:09
 */
@Service
public class ShopUserServiceImpl extends ServiceImpl<ShopUserMapper, ShopUser> implements ShopUserService {

    @Autowired
    private ShopCartService cartService;

    @Autowired
    private ShopOrderService orderService;

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> getInfo(Long id) {
        ShopUser shopUser = this.getById(id);
        QueryWrapper<ShopCart> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopCart.COL_ID_USER, id);
        Long cartCount = Long.valueOf(cartService.count(wrapper));
        QueryWrapper<ShopOrder> wrapper1 = new QueryWrapper<>();
        wrapper1.eq(ShopOrder.COL_ID_USER, id);
        Long orderCount = Long.valueOf(orderService.count(wrapper1));
        ShopUser shopUser1 = new ShopUser();
        BeanUtils.copyProperties(shopUser, shopUser1, "password", "salt");
        Map<String, Object> data = Maps.newHashMap(
                "cartCount", cartCount,
                "orderCount", orderCount,
                "info", shopUser1
        );
        return data;
    }
}
