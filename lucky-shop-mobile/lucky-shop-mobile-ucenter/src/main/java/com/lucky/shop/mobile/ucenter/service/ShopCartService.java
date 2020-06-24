package com.lucky.shop.mobile.ucenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.mobile.ucenter.domain.ShopCart;
import com.lucky.shop.mobile.ucenter.domain.vo.CartVo;

import java.util.List;

/**
 * 购物车
 *
 * @Author Guo Huaijian
 * @Date 2020/6/23 23:03
 */
public interface ShopCartService extends IService<ShopCart> {

    /**
     * 获取用户购物车
     *
     * @return
     */
    List<ShopCart> getByUser();

    /**
     * 添加购物车
     *
     * @param cartVo
     * @return
     */
    Integer add(CartVo cartVo);

    /**
     * 购物车数量
     *
     * @return
     */
    int cartCount();

    /**
     * 修改购物车
     *
     * @param id
     * @param count
     */
    void update(Long id, String count);

    /**
     * 删除购物车
     *
     * @param id
     * @return
     */
    void remove(Long id);

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
