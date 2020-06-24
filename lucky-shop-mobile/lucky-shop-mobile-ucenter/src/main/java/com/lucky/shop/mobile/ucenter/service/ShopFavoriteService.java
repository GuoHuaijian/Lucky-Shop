package com.lucky.shop.mobile.ucenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.mobile.ucenter.domain.ShopFavorite;

/**
 * 用户收藏
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 2:42
 */
public interface ShopFavoriteService extends IService<ShopFavorite> {

    /**
     * 添加收藏
     *
     * @param idGoods
     * @return
     */
    void add(Long idGoods);

    /**
     * 获取收藏
     *
     * @param idGoods
     * @return
     */
    ShopFavorite ifLike(Long idGoods);

}
