package com.lucky.shop.admin.mall.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.shop.admin.mall.domain.ShopCart;
import com.lucky.shop.admin.mall.service.ShopCartService;
import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.common.core.factory.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 购物车
 *
 * @Author Guo Huaijian
 * @Date 2020/6/5 12:58
 */
@RestController
@RequestMapping("mall/shop/cart")
public class ShopCartController {

    @Autowired
    private ShopCartService cartService;

    /**
     * 购物车列表
     *
     * @return
     */
    @GetMapping(value = "/list")
    public ResponseResult list() {
        Page<ShopCart> page = new PageFactory<ShopCart>().defaultPage();
        IPage<ShopCart> shopCarts = cartService.page(page);
        return ResponseResult.success(shopCarts);
    }
}
