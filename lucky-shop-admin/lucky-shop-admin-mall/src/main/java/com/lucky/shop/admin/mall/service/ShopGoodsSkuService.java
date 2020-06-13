package com.lucky.shop.admin.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.admin.mall.domain.ShopGoodsSku;

/**
 * 商品SKU
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 21:18
 */
public interface ShopGoodsSkuService extends IService<ShopGoodsSku> {

    /**
     * 获取商品SKU
     *
     * @return
     */
    Page<ShopGoodsSku> goodsSkuList();

    /**
     * 编辑商品SKU
     *
     * @param sku
     * @return
     */
    ShopGoodsSku saveSku(ShopGoodsSku sku);

    /**
     * 删除商品SKU
     *
     * @param id
     * @return
     */
    void remove(Long id);
}
