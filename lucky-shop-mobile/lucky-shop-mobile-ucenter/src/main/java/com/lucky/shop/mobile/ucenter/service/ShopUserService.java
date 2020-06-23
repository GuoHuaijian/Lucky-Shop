package com.lucky.shop.mobile.ucenter.service;

import com.lucky.shop.mobile.ucenter.domain.ShopUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 0:46
 */
public interface ShopUserService extends IService<ShopUser> {

    /**
     * 通过登录账号获取用户
     *
     * @param account
     * @return
     */
    ShopUser getUserByAccount(String account);

}
