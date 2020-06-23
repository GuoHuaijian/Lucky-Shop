package com.lucky.shop.mobile.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.mobile.ucenter.domain.ShopUser;
import com.lucky.shop.mobile.ucenter.mapper.ShopUserMapper;
import com.lucky.shop.mobile.ucenter.service.ShopUserService;
import org.springframework.stereotype.Service;

/**
 * 用户
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 0:46
 */
@Service
public class ShopUserServiceImpl extends ServiceImpl<ShopUserMapper, ShopUser> implements ShopUserService {

    /**
     * 通过登录账号获取用户
     *
     * @param account
     * @return
     */
    @Override
    public ShopUser getUserByAccount(String account) {
        QueryWrapper<ShopUser> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopUser.COL_MOBILE, account);
        return this.getOne(wrapper);
    }
}
