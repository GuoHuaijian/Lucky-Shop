package com.lucky.shop.admin.mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.shop.admin.mall.domain.ShopUser;
import com.lucky.shop.admin.mall.service.ShopUserService;
import com.lucky.shop.common.dto.ResponseResult;
import com.lucky.shop.common.factory.PageFactory;
import com.lucky.shop.common.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商城会员控制层
 * @Create by Guo Huaijian
 * @Since 2020/6/2 20:39
 */
@RestController
@RequestMapping("/shop/user")
public class ShopUserController {

    @Autowired
    private ShopUserService shopUserService;

    /**
     * 查询用户列表
     *
     * @return
     */
    @GetMapping("list")
    public ResponseResult list(@RequestParam(required = false) String mobile, @RequestParam(required = false) String nickName,
                                         @RequestParam(required = false) String startRegDate,
                                         @RequestParam(required = false) String endRegDate,
                                         @RequestParam(required = false) String startLastLoginTime,
                                         @RequestParam(required = false) String endLastLoginTime) {

        Page<ShopUser> page = new PageFactory<ShopUser>().defaultPage();
        QueryWrapper<ShopUser> wrapper = new QueryWrapper<>();
        wrapper.like(ShopUser.COL_MOBILE, mobile);
        wrapper.like(ShopUser.COL_NICK_NAME, nickName);
        if (StringUtil.isNotEmpty(startRegDate)) {
            wrapper.ge(ShopUser.COL_CREATE_TIME, startRegDate);
        }
        if (StringUtil.isNotEmpty(endRegDate)) {
            wrapper.le(ShopUser.COL_CREATE_TIME, endRegDate);
        }
        if (StringUtil.isNotEmpty(startLastLoginTime)) {
            wrapper.ge(ShopUser.COL_LAST_LOGIN_TIME, startLastLoginTime);
        }
        if (StringUtil.isNotEmpty(endLastLoginTime)) {
            wrapper.le(ShopUser.COL_LAST_LOGIN_TIME, endLastLoginTime);
        }
        IPage<ShopUser> shopUsers = shopUserService.page(page, wrapper);
        return ResponseResult.success(shopUsers);
    }
}
