package com.lucky.shop.mobile.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.mobile.product.domain.ShopGoodsSku;
import com.lucky.shop.mobile.product.mapper.ShopGoodsSkuMapper;
import com.lucky.shop.mobile.product.service.ShopGoodsSkuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品SKU
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 2:00
 */
@Service
public class ShopGoodsSkuServiceImpl extends ServiceImpl<ShopGoodsSkuMapper, ShopGoodsSku> implements ShopGoodsSkuService {

    /**
     * 根据id查询商品SKU
     *
     * @param id
     * @return
     */
    @Override
    public List<ShopGoodsSku> queryAll(Long id) {
        QueryWrapper<ShopGoodsSku> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopGoodsSku.COL_ID_GOODS, id);
        return this.list(wrapper);
    }
}
