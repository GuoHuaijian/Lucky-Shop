package com.lucky.shop.mobile.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.mobile.product.domain.ShopAttrKey;

import java.util.List;

/**
 * 商品属性名
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 2:07
 */
public interface ShopAttrKeyService extends IService<ShopAttrKey> {

    /**
     * 根据分类id查询商品属性名
     *
     * @param idCategory
     * @return
     */
    List<ShopAttrKey> queryBy(Long idCategory);
}
