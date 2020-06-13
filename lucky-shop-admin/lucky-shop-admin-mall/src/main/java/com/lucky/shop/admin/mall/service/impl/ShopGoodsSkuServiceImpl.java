package com.lucky.shop.admin.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.mall.domain.ShopGoodsSku;
import com.lucky.shop.admin.mall.mapper.ShopGoodsSkuMapper;
import com.lucky.shop.admin.mall.service.ShopGoodsSkuService;
import com.lucky.shop.common.core.factory.PageFactory;
import com.lucky.shop.common.core.tool.LogObjectHolder;
import com.lucky.shop.common.core.utils.Lists;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 商品SKU
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 21:18
 */
@Service
public class ShopGoodsSkuServiceImpl extends ServiceImpl<ShopGoodsSkuMapper, ShopGoodsSku> implements ShopGoodsSkuService {

    /**
     * 获取商品SKU
     *
     * @return
     */
    @Override
    public Page<ShopGoodsSku> goodsSkuList() {
        Page<ShopGoodsSku> page = new PageFactory<ShopGoodsSku>().defaultPage();
        QueryWrapper<ShopGoodsSku> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopGoodsSku.COL_IS_DELETED, false);
        IPage<ShopGoodsSku> skuIPage = this.page(page, wrapper);
        return (Page<ShopGoodsSku>) skuIPage;
    }

    /**
     * 编辑商品SKU
     *
     * @param sku
     * @return
     */
    @Override
    public ShopGoodsSku saveSku(ShopGoodsSku sku) {
        List<String> codeArr = Arrays.asList(sku.getCode().split(","));
        List<String> codeNameArr = Arrays.asList(sku.getCodeName().split(","));
        Collections.sort(codeArr, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
        Collections.sort(codeNameArr, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
        String code = Lists.concat(codeArr, ",");
        String codeName = Lists.concat(codeNameArr, ",");
        sku.setCode(code);
        sku.setCodeName(codeName);
        QueryWrapper<ShopGoodsSku> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopGoodsSku.COL_ID_GOODS, sku.getIdGoods());
        wrapper.eq(ShopGoodsSku.COL_CODE, code);
        ShopGoodsSku oldSku = this.getOne(wrapper);
        if (oldSku != null) {
            LogObjectHolder.me().set(oldSku);
            oldSku.setMarketingPrice(sku.getMarketingPrice());
            oldSku.setPrice(sku.getPrice());
            oldSku.setStock(sku.getStock());
            this.updateById(oldSku);
            return oldSku;
        } else {
            this.save(sku);
            return sku;
        }
    }

    /**
     * 删除商品SKU
     *
     * @param id
     */
    @Override
    public void remove(Long id) {
        ShopGoodsSku sku = this.getById(id);
        sku.setIsDeleted(true);
        this.updateById(sku);
    }
}
