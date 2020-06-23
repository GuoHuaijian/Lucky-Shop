package com.lucky.shop.mobile.ucenter.controller;

import com.lucky.shop.mobile.ucenter.domain.ShopCart;
import com.lucky.shop.mobile.ucenter.service.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 购物车
 *
 * @Author Guo Huaijian
 * @Date 2020/6/23 23:03
 */
@RestController
@RequestMapping("/user/cart")
public class ShopCartController {

    @Autowired
    private ShopCartService cartService;

    /**
     * 查询用户购物车
     *
     * @param idUser
     * @return
     */
    @GetMapping("/{idUser}")
    public List<ShopCart> queryAll(@PathVariable String idUser) {
        return cartService.queryAll(idUser);
    }

    /**
     * 删除全部购物车
     *
     * @param id
     */
    @DeleteMapping("/deleteCart")
    public void deleteAll(List<Long> id) {
        cartService.deleteAll(id);
    }
}
