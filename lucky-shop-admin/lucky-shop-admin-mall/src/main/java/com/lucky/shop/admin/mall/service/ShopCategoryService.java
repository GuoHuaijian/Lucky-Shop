package com.lucky.shop.admin.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.admin.mall.domain.Banner;
import com.lucky.shop.admin.mall.domain.ShopAttrKey;
import com.lucky.shop.admin.mall.domain.ShopCategory;

import java.util.List;

/**
 * 商品类别
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 21:18
 */
public interface ShopCategoryService extends IService<ShopCategory> {

    /**
     * 商品类别列表
     *
     * @return
     */
    Page<ShopCategory> categoryList();

    /**
     * 获取全部商品列表
     *
     * @return
     */
    List<ShopCategory> getAll();

    /**
     * 编辑商品类别
     *
     * @param category
     * @return
     */
    void saveCategory(ShopCategory category);

    /**
     * 删除商品类别
     *
     * @param id
     * @return
     */
    void remove(Long id);

    /**
     * 获取Banner
     *
     * @param idCategory
     * @return
     */
    List<Banner> getBanners(Long idCategory);

    /**
     * 获取商品属性值
     *
     * @param idCategory
     * @return
     */
    List<ShopAttrKey> getAttrKeys(Long idCategory);

    /**
     * 删除Banner
     *
     * @param idCategory
     * @param idBanner
     * @return
     */
    void removeBanner(Long idCategory, Long idBanner);

    /**
     * 设置Banner
     *
     * @param idCategory
     * @param idBanner
     * @return
     */
    String setBanner(Long idCategory, Long idBanner);
}
