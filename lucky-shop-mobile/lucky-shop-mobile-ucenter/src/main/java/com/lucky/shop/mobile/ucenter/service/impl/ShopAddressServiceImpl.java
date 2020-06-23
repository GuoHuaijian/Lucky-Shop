package com.lucky.shop.mobile.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.mobile.ucenter.domain.ShopAddress;
import com.lucky.shop.mobile.ucenter.mapper.ShopAddressMapper;
import com.lucky.shop.mobile.ucenter.service.ShopAddressService;
import org.springframework.stereotype.Service;

/**
 * 收货地址
 *
 * @Author Guo Huaijian
 * @Date 2020/6/23 23:40
 */
@Service
public class ShopAddressServiceImpl extends ServiceImpl<ShopAddressMapper, ShopAddress> implements ShopAddressService {

    /**
     * 获取用户默认收货地址
     *
     * @param idUser
     * @return
     */
    @Override
    public ShopAddress getDefaultAddr(Long idUser) {
        QueryWrapper<ShopAddress> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopAddress.COL_ID_USER, idUser);
        wrapper.eq(ShopAddress.COL_IS_DEFAULT, true);
        return this.getOne(wrapper);
    }

    /**
     * 根据主键获取地址
     *
     * @param chosenAddressId
     * @return
     */
    @Override
    public ShopAddress get(Long chosenAddressId) {
        return this.getById(chosenAddressId);
    }
}
