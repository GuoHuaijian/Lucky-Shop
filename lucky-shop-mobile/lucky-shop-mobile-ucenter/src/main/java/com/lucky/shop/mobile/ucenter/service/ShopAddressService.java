package com.lucky.shop.mobile.ucenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.mobile.ucenter.domain.ShopAddress;

import java.util.List;

/**
 * 收货地址
 *
 * @Author Guo Huaijian
 * @Date 2020/6/23 23:40
 */
public interface ShopAddressService extends IService<ShopAddress> {

    /**
     * 获取地址
     *
     * @param id
     * @return
     */
    ShopAddress getById(Long id);

    /**
     * 删除地址
     *
     * @param id
     */
    void remove(Long id);

    /**
     * 修改默认地址
     *
     * @param id
     * @param isDefault
     * @return
     */
    ShopAddress changeDefault(Long id, Boolean isDefault);

    /**
     * 根据用户获取地址
     *
     * @return
     */
    List<ShopAddress> getByUser();

    /**
     * 编辑地址
     *
     * @param addressInfo
     */
    void saveAddress(ShopAddress addressInfo);

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
