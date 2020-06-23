package com.lucky.shop.mobile.product.controller;

import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.mobile.product.domain.ShopCategory;
import com.lucky.shop.mobile.product.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品类别
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 1:27
 */
@RestController
@RequestMapping("/category")
public class ShopCategoryController {

    @Autowired
    private ShopCategoryService categoryService;

    /**
     * 获取分类列表
     *
     * @return
     */
    @GetMapping(value = "/list")
    public ResponseResult list() {
        List<ShopCategory> list = categoryService.categoryList();
        return ResponseResult.success(list);
    }
}
