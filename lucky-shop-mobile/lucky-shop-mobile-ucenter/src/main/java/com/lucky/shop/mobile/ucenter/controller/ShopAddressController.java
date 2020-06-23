package com.lucky.shop.mobile.ucenter.controller;

import com.lucky.shop.mobile.ucenter.domain.ShopAddress;
import com.lucky.shop.mobile.ucenter.service.ShopAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 收货地址
 *
 * @Author Guo Huaijian
 * @Date 2020/6/23 23:40
 */
@RestController
@RequestMapping("/user/address")
public class ShopAddressController {

    @Autowired
    private ShopAddressService addressService;

    /**
     * 获取用户默认收货地址
     *
     * @param idUser
     * @return
     */
    @GetMapping("/{idUser}")
    public ShopAddress getDefaultAddr(@PathVariable Long idUser) {
        return addressService.getDefaultAddr(idUser);
    }

    /**
     * 根据主键获取地址
     *
     * @param chosenAddressId
     * @return
     */
    @GetMapping("/{chosenAddressId}")
    public ShopAddress get(@PathVariable Long chosenAddressId) {
        return addressService.get(chosenAddressId);
    }
}
