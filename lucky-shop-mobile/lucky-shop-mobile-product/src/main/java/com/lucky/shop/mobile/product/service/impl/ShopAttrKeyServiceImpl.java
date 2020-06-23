package com.lucky.shop.mobile.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.mobile.product.domain.ShopAttrKey;
import com.lucky.shop.mobile.product.mapper.ShopAttrKeyMapper;
import com.lucky.shop.mobile.product.service.ShopAttrKeyService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品属性名
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 2:07
 */
@Service
public class ShopAttrKeyServiceImpl extends ServiceImpl<ShopAttrKeyMapper, ShopAttrKey> implements ShopAttrKeyService {

    /**
     * 根据分类id查询商品属性名
     *
     * @param idCategory
     * @return
     */
    @Override
    public List<ShopAttrKey> queryBy(Long idCategory) {
        QueryWrapper<ShopAttrKey> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopAttrKey.COL_ID_CATEGORY, idCategory);
        return this.list(wrapper);
    }
}
