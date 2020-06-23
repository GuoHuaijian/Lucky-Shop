package com.lucky.shop.mobile.ucenter.service;

import com.lucky.shop.mobile.ucenter.domain.ShopAddress;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 收货地址
 *
 * @Author Guo Huaijian
 * @Date 2020/6/23 23:40
 */
public interface ShopAddressService extends IService<ShopAddress> {

    /**
     * 获取用户默认收货地址
     *
     * @param idUser
     * @return
     */
    ShopAddress getDefaultAddr(Long idUser);

    /**
     * 根据主键获取地址
     *
     * @param chosenAddressId
     * @return
     */
    ShopAddress get(Long chosenAddressId);

}
