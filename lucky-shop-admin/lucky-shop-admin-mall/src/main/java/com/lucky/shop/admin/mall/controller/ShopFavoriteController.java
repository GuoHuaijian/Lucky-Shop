package com.lucky.shop.admin.mall.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.shop.admin.mall.domain.ShopFavorite;
import com.lucky.shop.admin.mall.service.ShopFavoriteService;
import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.common.core.factory.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户收藏
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 22:58
 */
@RestController
@RequestMapping("mall/shop/favorite")
public class ShopFavoriteController {

    @Autowired
    private ShopFavoriteService favoriteService;

    /**
     * 用户收藏列表
     *
     * @return
     */
    @GetMapping(value = "/list")
    public ResponseResult list() {
        Page<ShopFavorite> page = new PageFactory<ShopFavorite>().defaultPage();
        IPage<ShopFavorite> shopFavoritePage = favoriteService.page(page);
        return ResponseResult.success(shopFavoritePage);
    }

}
