package com.lucky.shop.admin.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.admin.mall.domain.ShopAddress;

/**
 * 收获地址
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 19:36
 */
public interface ShopAddressService extends IService<ShopAddress> {

    /**
     * 根据用户id分页查询地址
     *
     * @param idUser
     * @return
     */
    Page<ShopAddress> addressList(Long idUser);


}
