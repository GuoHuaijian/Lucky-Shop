package com.lucky.shop.admin.mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.shop.admin.mall.domain.ShopUser;
import com.lucky.shop.admin.mall.service.ShopUserService;
import com.lucky.shop.common.dto.ResponseResult;
import com.lucky.shop.common.factory.PageFactory;
import com.lucky.shop.common.utils.DateUtil;
import com.lucky.shop.common.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 商城会员控制层
 *
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
     * @param mobile
     * @param nickName
     * @param startRegDate
     * @param endRegDate
     * @param startLastLoginTime
     * @param endLastLoginTime
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
        if (StringUtil.isNotEmpty(mobile)) {
            wrapper.like(ShopUser.COL_MOBILE, mobile);
        }
        if (StringUtil.isNotEmpty(nickName)) {
            wrapper.like(ShopUser.COL_NICK_NAME, nickName);
        }
        if (StringUtil.isNotEmpty(startRegDate)) {
            wrapper.ge(ShopUser.COL_CREATE_TIME, DateUtil.parseTime(startRegDate + " 00:00:00"));
        }
        if (StringUtil.isNotEmpty(endRegDate)) {
            wrapper.le(ShopUser.COL_CREATE_TIME, DateUtil.parseTime(endRegDate + " 23:59:59"));
        }
        if (StringUtil.isNotEmpty(startLastLoginTime)) {
            wrapper.ge(ShopUser.COL_LAST_LOGIN_TIME, DateUtil.parseTime(startLastLoginTime + " 00:00:00"));
        }
        if (StringUtil.isNotEmpty(endLastLoginTime)) {
            wrapper.le(ShopUser.COL_LAST_LOGIN_TIME, DateUtil.parseTime(endLastLoginTime + " 23:59:59"));
        }
        IPage<ShopUser> shopUsers = shopUserService.page(page, wrapper);
        return ResponseResult.success(shopUsers);
    }

    /**
     * 根据id获取会员
     *
     * @param id
     * @return
     */
    @GetMapping(value = "{id}")
    public ResponseResult get(@PathVariable("id") Long id) {
        ShopUser shopUser = shopUserService.getById(id);
        return ResponseResult.success(shopUser);
    }

    /**
     * 获取会员信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/info/{id}")
    public ResponseResult getInfo(@PathVariable("id") Long id) {
        Map data = shopUserService.getInfo(id);
        return ResponseResult.success(data);
    }
}
