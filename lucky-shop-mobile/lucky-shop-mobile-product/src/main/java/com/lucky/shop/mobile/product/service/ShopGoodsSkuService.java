package com.lucky.shop.mobile.product.service;

import com.lucky.shop.mobile.product.domain.ShopGoodsSku;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 商品SKU
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 2:00
 */
public interface ShopGoodsSkuService extends IService<ShopGoodsSku> {

    /**
     * 根据id查询商品SKU
     *
     * @param id
     * @return
     */
    List<ShopGoodsSku> queryAll(Long id);

}
