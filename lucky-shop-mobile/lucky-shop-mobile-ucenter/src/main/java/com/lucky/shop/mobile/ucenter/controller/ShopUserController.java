package com.lucky.shop.mobile.ucenter.controller;

import com.lucky.shop.mobile.ucenter.domain.ShopUser;
import com.lucky.shop.mobile.ucenter.service.ShopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 0:48
 */
@RestController
@RequestMapping("/user")
public class ShopUserController {

    @Autowired
    private ShopUserService userService;

    /**
     * 通过登录账号获取用户
     *
     * @param account
     * @return
     */
    @GetMapping("/account/{account}")
    public ShopUser getUserByAccount(@PathVariable String account) {
        return userService.getUserByAccount(account);
    }
}
