package com.lucky.shop.admin.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.admin.mall.domain.ShopAttrKey;

/**
 * 商品属性名
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 20:03
 */
public interface ShopAttrKeyService extends IService<ShopAttrKey> {

    /**
     * 分页获取商品属性名
     *
     * @return
     */
    Page<ShopAttrKey> attrKeyList();

    /**
     * 保存或修改商品属性名
     *
     * @param shopAttrKey
     */
    void SaveOrUpdateAttrKey(ShopAttrKey shopAttrKey);

    /**
     * 删除商品属性名
     *
     * @param id
     */
    void remove(Long id);

    /**
     * 修改商品属性名
     *
     * @param id
     * @param attrName
     */
    void updateAttrName(Long id, String attrName);


}
