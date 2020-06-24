package com.lucky.shop.mobile.product.controller;

import com.lucky.shop.mobile.product.domain.ShopGoodsSku;
import com.lucky.shop.mobile.product.service.ShopGoodsSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品SKU
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 14:19
 */
@RestController
@RequestMapping("/goods/sku")
public class ShopGoodsSkuController {

    @Autowired
    private ShopGoodsSkuService goodsSkuService;

    /**
     * 根据id获取商品SKU
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ShopGoodsSku getOne(@PathVariable Long id) {
        return goodsSkuService.getById(id);
    }
}
