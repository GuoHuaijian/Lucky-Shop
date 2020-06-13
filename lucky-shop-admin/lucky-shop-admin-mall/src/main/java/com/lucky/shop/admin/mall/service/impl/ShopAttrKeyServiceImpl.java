package com.lucky.shop.admin.mall.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.mall.domain.ShopAttrKey;
import com.lucky.shop.admin.mall.mapper.ShopAttrKeyMapper;
import com.lucky.shop.admin.mall.service.ShopAttrKeyService;
import com.lucky.shop.common.core.factory.PageFactory;
import org.springframework.stereotype.Service;

/**
 * 商品属性名
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 20:03
 */
@Service
public class ShopAttrKeyServiceImpl extends ServiceImpl<ShopAttrKeyMapper, ShopAttrKey> implements ShopAttrKeyService {

    /**
     * 分页获取商品属性名
     *
     * @return
     */
    @Override
    public Page<ShopAttrKey> attrKeyList() {
        Page<ShopAttrKey> page = new PageFactory<ShopAttrKey>().defaultPage();
        IPage<ShopAttrKey> shopAttrKeyPage = this.page(page);
        return (Page<ShopAttrKey>) shopAttrKeyPage;
    }

    /**
     * 保存或修改商品属性名
     *
     * @param shopAttrKey
     */
    @Override
    public void SaveOrUpdateAttrKey(ShopAttrKey shopAttrKey) {
        this.saveOrUpdate(shopAttrKey);
    }

    /**
     * 删除商品属性名
     *
     * @param id
     */
    @Override
    public void remove(Long id) {
        this.removeById(id);
    }

    /**
     * 修改商品属性名
     *
     * @param id
     * @param attrName
     */
    @Override
    public void updateAttrName(Long id, String attrName) {
        ShopAttrKey attrKey = this.getById(id);
        attrKey.setAttrName(attrName);
        this.updateById(attrKey);
    }
}
