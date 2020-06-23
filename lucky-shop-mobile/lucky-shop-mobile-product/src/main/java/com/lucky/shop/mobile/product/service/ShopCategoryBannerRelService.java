package com.lucky.shop.mobile.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.mobile.product.domain.ShopCategoryBannerRel;

import java.util.List;

/**
 * 类别banner关联表
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 1:27
 */
public interface ShopCategoryBannerRelService extends IService<ShopCategoryBannerRel> {

    /**
     * 根据分类id查询列表
     *
     * @param idCategory
     * @return
     */
    List<ShopCategoryBannerRel> queryAll(Long idCategory);

}
