package com.lucky.shop.admin.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.mall.domain.ShopGoods;
import com.lucky.shop.admin.mall.domain.ShopGoodsSku;
import com.lucky.shop.admin.mall.domain.vo.GoodsVo;
import com.lucky.shop.admin.mall.mapper.ShopGoodsMapper;
import com.lucky.shop.admin.mall.service.ShopGoodsService;
import com.lucky.shop.admin.mall.service.ShopGoodsSkuService;
import com.lucky.shop.common.core.factory.PageFactory;
import com.lucky.shop.common.core.tool.LogObjectHolder;
import com.lucky.shop.common.core.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 21:18
 */
@Service
public class ShopGoodsServiceImpl extends ServiceImpl<ShopGoodsMapper, ShopGoods> implements ShopGoodsService {

    @Autowired
    private ShopGoodsSkuService goodsSkuService;

    /**
     * 商品列表
     *
     * @param name
     * @return
     */
    @Override
    public Page<ShopGoods> goodsList(String name) {
        Page<ShopGoods> page = new PageFactory<ShopGoods>().defaultPage();
        QueryWrapper<ShopGoods> wrapper = new QueryWrapper<>();
        if(StringUtil.isNotEmpty(name)){
            wrapper.like(ShopGoods.COL_NAME, name);
        }
        IPage<ShopGoods> goodsIPage = this.page(page, wrapper);
        return (Page<ShopGoods>) goodsIPage;
    }

    /**
     * 保存商品基本信息
     *
     * @param goods
     * @return
     */
    @Override
    public Long saveBaseInfo(ShopGoods goods) {
        if (goods.getId() == null) {
            this.save(goods);
        }
        return goods.getId();
    }

    /**
     * 编辑商品
     *
     * @param goods
     */
    @Override
    public void saveOrUpdateGoods(ShopGoods goods) {
        QueryWrapper<ShopGoodsSku> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopGoodsSku.COL_ID_GOODS, goods.getId());
        wrapper.eq(ShopGoodsSku.COL_IS_DELETED, false);
        List<ShopGoodsSku> skuList = goodsSkuService.list(wrapper);
        if (goods.getPrice() == null) {
            if (!skuList.isEmpty()) {
                int stock = 0;
                for (ShopGoodsSku sku : skuList) {
                    stock += sku.getStock();
                }
                goods.setStock(stock);
                goods.setPrice(skuList.get(0).getPrice());
            } else {
                throw new RuntimeException();
            }
        } else {
            if (!skuList.isEmpty()) {
                // 如果配置了price，说明是单规格商品，则将之前配置的sku库存皆设置为0;这里最好不要删除sku记录，避免之前下过的订单无法正确关联
                for (ShopGoodsSku sku : skuList) {
                    sku.setStock(0);
                }
                goodsSkuService.updateBatchById(skuList);
            }
        }
        if (goods.getId() == null) {
            this.save(goods);
        } else {
            ShopGoods old = this.getById(goods.getId());
            LogObjectHolder.me().set(old);
            goods.setCreateBy(old.getCreateBy());
            goods.setCreateTime(old.getCreateTime());
            this.updateById(goods);
        }
    }

    /**
     * 删除商品
     *
     * @param id
     */
    @Override
    public void remove(Long id) {
        this.removeById(id);
    }

    /**
     * 获取商品详情
     *
     * @param id
     * @return
     */
    @Override
    public GoodsVo getDetail(Long id) {
        ShopGoods goods = getById(id);
        QueryWrapper<ShopGoodsSku> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopGoodsSku.COL_ID_GOODS, id);
        wrapper.eq(ShopGoodsSku.COL_IS_DELETED, false);
        List<ShopGoodsSku> skuList = goodsSkuService.list(wrapper);
        GoodsVo vo = new GoodsVo();
        vo.setGoods(goods);
        vo.setSkuList(skuList);
        return vo;
    }

    /**
     * 上架/下架商品
     *
     * @param id
     * @param isOnSale
     * @return
     */
    @Override
    public ShopGoods changeIsOnSale(Long id, Boolean isOnSale) {
        ShopGoods goods = getById(id);
        goods.setIsOnSale(isOnSale);
        updateById(goods);
        return this.getById(id);
    }

}
