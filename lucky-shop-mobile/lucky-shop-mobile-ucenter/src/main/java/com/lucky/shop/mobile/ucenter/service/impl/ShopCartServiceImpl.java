package com.lucky.shop.mobile.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.mobile.ucenter.domain.ShopCart;
import com.lucky.shop.mobile.ucenter.mapper.ShopCartMapper;
import com.lucky.shop.mobile.ucenter.service.ShopCartService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 购物车
 *
 * @Author Guo Huaijian
 * @Date 2020/6/23 23:03
 */
@Service
public class ShopCartServiceImpl extends ServiceImpl<ShopCartMapper, ShopCart> implements ShopCartService {

    /**
     * 查询用户购物车
     *
     * @param idUser
     * @return
     */
    @Override
    public List<ShopCart> queryAll(String idUser) {
        QueryWrapper<ShopCart> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopCart.COL_ID_USER, idUser);
        return this.list(wrapper);
    }

    /**
     * 删除全部购物车
     *
     * @param id
     */
    @Override
    public void deleteAll(List<Long> id) {
        this.removeByIds(id);
    }
}
