package com.lucky.shop.mobile.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.mobile.product.domain.ShopCategory;

import java.util.List;

/**
 * 商品类别
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 1:27
 */
public interface ShopCategoryService extends IService<ShopCategory> {

    /**
     * 获取分类列表
     *
     * @return
     */
    List<ShopCategory> categoryList();

}
