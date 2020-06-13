package com.lucky.shop.admin.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.admin.mall.domain.ShopAttrVal;

import java.util.List;
import java.util.Map;

/**
 * 商品属性值
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 20:28
 */
public interface ShopAttrValService extends IService<ShopAttrVal> {

    /**
     * 获取属性键值对
     *
     * @param idCategory
     * @return
     */
    Map getAttrByCategoryAndGoods(Long idCategory);

    /**
     * 获取商品属性值列表
     *
     * @param idAttrKey
     * @return
     */
    List<ShopAttrVal> getAttrVals(Long idAttrKey);

    /**
     * 编辑商品属性值
     *
     * @param idAttrKey
     * @param id
     * @param attrVal
     * @return
     */
    Object save(Long idAttrKey, Long id, String attrVal);

    /**
     * 删除商品属性值
     *
     * @param id
     */
    void remove(Long id);

    /**
     * 修改商品属性值
     *
     * @param id
     * @param attrVal
     */
    void updateAttrName(Long id, String attrVal);

}
