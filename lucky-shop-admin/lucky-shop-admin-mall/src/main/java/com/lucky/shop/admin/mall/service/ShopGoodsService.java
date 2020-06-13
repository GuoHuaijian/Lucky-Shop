package com.lucky.shop.admin.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.admin.mall.domain.ShopGoods;
import com.lucky.shop.admin.mall.domain.vo.GoodsVo;

/**
 * 商品
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 21:18
 */
public interface ShopGoodsService extends IService<ShopGoods> {

    /**
     * 商品列表
     *
     * @param name
     * @return
     */
    Page<ShopGoods> goodsList(String name);

    /**
     * 保存商品基本信息
     *
     * @param goods
     * @return
     */
    Long saveBaseInfo(ShopGoods goods);

    /**
     * 编辑商品
     *
     * @param goods
     * @return
     */
    void saveOrUpdateGoods(ShopGoods goods);

    /**
     * 删除商品
     *
     * @param id
     * @return
     */
    void remove(Long id);

    /**
     * 获取商品详情
     *
     * @param id
     * @return
     */
    GoodsVo getDetail(Long id);

    /**
     * 上架/下架商品
     *
     * @param id
     * @param isOnSale
     * @return
     */
    ShopGoods changeIsOnSale(Long id, Boolean isOnSale);
}
