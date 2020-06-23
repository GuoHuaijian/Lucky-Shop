package com.lucky.shop.mobile.ucenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.mobile.ucenter.domain.ShopCart;

import java.util.List;

/**
 * 购物车
 *
 * @Author Guo Huaijian
 * @Date 2020/6/23 23:03
 */
public interface ShopCartService extends IService<ShopCart> {

    /**
     * 查询用户购物车
     *
     * @param idUser
     * @return
     */
    List<ShopCart> queryAll(String idUser);

    /**
     * 删除全部购物车
     *
     * @param id
     */
    void deleteAll(List<Long> id);
}
