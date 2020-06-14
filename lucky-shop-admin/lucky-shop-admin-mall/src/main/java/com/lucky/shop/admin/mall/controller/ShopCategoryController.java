package com.lucky.shop.admin.mall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.shop.admin.mall.domain.Banner;
import com.lucky.shop.admin.mall.domain.ShopAttrKey;
import com.lucky.shop.admin.mall.domain.ShopCategory;
import com.lucky.shop.admin.mall.service.ShopCategoryService;
import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.common.log.annotation.BussinessLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品类别
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 21:27
 */
@RestController
@RequestMapping("/shop/category")
public class ShopCategoryController {

    @Autowired
    private ShopCategoryService categoryService;

    /**
     * 商品类别列表
     *
     * @return
     */
    @GetMapping(value = "/list")
    public ResponseResult list() {
        Page<ShopCategory> shopCategoryPage = categoryService.categoryList();
        return ResponseResult.success(shopCategoryPage);
    }

    /**
     * 获取全部商品列表
     *
     * @return
     */
    @GetMapping(value = "/getAll")
    public ResponseResult getAll() {
        List<ShopCategory> categories = categoryService.getAll();
        return ResponseResult.success(categories);
    }

    /**
     * 编辑商品类别
     *
     * @param category
     * @return
     */
    @PostMapping()
    @BussinessLog(value = "编辑商品类别", key = "name")
//    @RequiresPermissions(value = {Permission.CATEGORY_EDIT})
    public ResponseResult save(@ModelAttribute ShopCategory category) {
        categoryService.saveCategory(category);
        return ResponseResult.success();
    }

    /**
     * 删除商品类别
     *
     * @param id
     * @return
     */
    @DeleteMapping()
    @BussinessLog(value = "删除商品类别", key = "id")
//    @RequiresPermissions(value = {Permission.CATEGORY_EDIT})
    public ResponseResult remove(Long id) {
        categoryService.remove(id);
        return ResponseResult.success();
    }

    /**
     * 获取Banner
     *
     * @param idCategory
     * @return
     */
    @GetMapping(value = "/getBanners/{idCategory}")
    public ResponseResult getBanners(@PathVariable("idCategory") Long idCategory) {
        List<Banner> banners = categoryService.getBanners(idCategory);
        return ResponseResult.success(banners);
    }

    /**
     * 获取商品属性值
     *
     * @param idCategory
     * @return
     */
    @GetMapping(value = "getAttrKeys/{idCategory}")
    public ResponseResult getAttrKeys(@PathVariable("idCategory") Long idCategory) {
        List<ShopAttrKey> list = categoryService.getAttrKeys(idCategory);
        return ResponseResult.success(list);

    }

    /**
     * 删除Banner
     *
     * @param idCategory
     * @param idBanner
     * @return
     */
    @DeleteMapping(value = "/removeBanner/{idCategory}/{idBanner}")
//    @RequiresPermissions(value = {Permission.CATEGORY_EDIT})
    public ResponseResult removeBanner(@PathVariable("idCategory") Long idCategory,
                                       @PathVariable("idBanner") Long idBanner) {
        categoryService.removeBanner(idCategory, idBanner);
        return ResponseResult.success();
    }

    /**
     * 设置Banner
     *
     * @param idCategory
     * @param idBanner
     * @return
     */
    @PostMapping(value = "/setBanner/{idCategory}/{idBanner}")
//    @RequiresPermissions(value = {Permission.CATEGORY_EDIT})
    public ResponseResult setBanner(@PathVariable("idCategory") Long idCategory,
                                    @PathVariable("idBanner") Long idBanner) {
        categoryService.setBanner(idCategory, idBanner);
        return ResponseResult.success();
    }
}
