package com.lucky.shop.admin.mall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.shop.admin.mall.domain.ShopAddress;
import com.lucky.shop.admin.mall.service.ShopAddressService;
import com.lucky.shop.common.core.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 收获地址
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 19:36
 */
@RestController
@RequestMapping("mall/shop/address")
public class ShopAddressController {

    @Autowired
    private ShopAddressService addressService;

    /**
     * 获取收获地址
     *
     * @param idUser
     * @return
     */
    @GetMapping(value = "/list")
    public ResponseResult list(@RequestParam(required = false) Long idUser) {
        Page<ShopAddress> page = addressService.addressList(idUser);
        return ResponseResult.success(page);
    }
}
