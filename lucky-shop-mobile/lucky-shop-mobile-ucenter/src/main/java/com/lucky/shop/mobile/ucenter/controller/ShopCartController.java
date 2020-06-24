package com.lucky.shop.mobile.ucenter.controller;

import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.mobile.ucenter.domain.ShopCart;
import com.lucky.shop.mobile.ucenter.domain.vo.CartVo;
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
@RequestMapping("mobile/ucenter/user/cart")
public class ShopCartController {

    @Autowired
    private ShopCartService cartService;

    /**
     * 获取用户购物车
     *
     * @return
     */
    @GetMapping(value = "/queryByUser")
    public ResponseResult getByUser() {
        List<ShopCart> list = cartService.getByUser();
        return ResponseResult.success(list);
    }

    /**
     * 添加购物车
     *
     * @param cartVo
     * @return
     */
    @PostMapping(value = "/add")
    public ResponseResult add(@RequestBody CartVo cartVo) {
        Integer result = cartService.add(cartVo);
        return ResponseResult.success(result);
    }

    /**
     * 购物车数量
     *
     * @return
     */
    @GetMapping(value = "/count")
    public ResponseResult count() {
        int count = cartService.cartCount();
        return ResponseResult.success(count);
    }

    /**
     * 修改购物车
     *
     * @param id
     * @param count
     * @return
     */
    @PostMapping(value = "/update/{id}/{count}")
    public ResponseResult update(@PathVariable("id") Long id, @PathVariable("count") String count) {
        cartService.update(id, count);
        return ResponseResult.success();
    }

    /**
     * 删除购物车
     *
     * @param id
     * @return
     */
    @DeleteMapping()
    public ResponseResult remove(@RequestParam Long id) {
        cartService.remove(id);
        return ResponseResult.success();
    }

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
