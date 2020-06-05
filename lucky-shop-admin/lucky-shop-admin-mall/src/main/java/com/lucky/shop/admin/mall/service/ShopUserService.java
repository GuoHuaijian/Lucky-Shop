package com.lucky.shop.admin.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.admin.mall.domain.ShopUser;

import java.util.Map;

/**
 * 商城会员Service
 *
 * @Create by Guo Huaijian
 * @Since 2020/6/2 20:09
 */
public interface ShopUserService extends IService<ShopUser> {

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    Map getInfo(Long id);


}
