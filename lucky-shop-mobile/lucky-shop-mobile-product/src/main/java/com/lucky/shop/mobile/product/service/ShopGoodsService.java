package com.lucky.shop.mobile.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.mobile.product.domain.ShopGoods;

import java.util.List;
import java.util.Map;

/**
 * 商品
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 1:46
 */
public interface ShopGoodsService extends IService<ShopGoods> {

    /**
     * 获取指定类别下的商品列表
     *
     * @param idCategory
     * @return
     */
    Page<ShopGoods> queryGoods(Long idCategory);

    /**
     * 根据关键字搜索商品
     *
     * @param key
     * @return
     */
    Page<ShopGoods> search(String key);

    /**
     * 查询新上架
     *
     * @return
     */
    List<ShopGoods> searchNew();

    /**
     * 查询热门商品
     *
     * @return
     */
    List<ShopGoods> searchHot();

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    Map get(Long id);

}
