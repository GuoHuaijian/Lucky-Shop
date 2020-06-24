package com.lucky.shop.mobile.ucenter.controller;

import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.mobile.ucenter.domain.ShopFavorite;
import com.lucky.shop.mobile.ucenter.service.ShopFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户收藏
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 2:42
 */
@RestController
@RequestMapping("mobile/ucenter/user/favorite")
public class ShopFavoriteController {

    @Autowired
    private ShopFavoriteService favoriteService;

    /**
     * 添加收藏
     *
     * @param idGoods
     * @return
     */
    @PostMapping(value = "/add/{idGoods}")
    public ResponseResult add(@PathVariable("idGoods") Long idGoods) {
        favoriteService.add(idGoods);
        return ResponseResult.success();
    }

    /**
     * 获取收藏
     *
     * @param idGoods
     * @return
     */
    @GetMapping(value = "/ifLike/{idGoods}")
    public ResponseResult ifLike(@PathVariable("idGoods") Long idGoods) {
        ShopFavorite shopFavorite = favoriteService.ifLike(idGoods);
        return ResponseResult.success(shopFavorite != null);
    }

}
