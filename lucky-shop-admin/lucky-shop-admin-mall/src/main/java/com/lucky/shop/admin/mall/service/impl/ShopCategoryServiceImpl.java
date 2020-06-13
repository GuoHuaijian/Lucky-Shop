package com.lucky.shop.admin.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.mall.domain.*;
import com.lucky.shop.admin.mall.mapper.ShopCategoryMapper;
import com.lucky.shop.admin.mall.service.ShopAttrKeyService;
import com.lucky.shop.admin.mall.service.ShopCategoryBannerRelService;
import com.lucky.shop.admin.mall.service.ShopCategoryService;
import com.lucky.shop.admin.mall.service.ShopGoodsService;
import com.lucky.shop.common.core.factory.PageFactory;
import com.lucky.shop.common.core.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品类别
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 21:18
 */
@Service
public class ShopCategoryServiceImpl extends ServiceImpl<ShopCategoryMapper, ShopCategory> implements ShopCategoryService {

    @Autowired
    private ShopGoodsService goodsService;

    @Autowired
    private ShopCategoryBannerRelService categoryBannerRelService;

    @Autowired
    private ShopAttrKeyService attrKeyService;

    /**
     * 商品类别列表
     *
     * @return
     */
    @Override
    public Page<ShopCategory> categoryList() {
        Page<ShopCategory> page = new PageFactory<ShopCategory>().defaultPage();
        IPage<ShopCategory> ShopCategoryPage = this.page(page);
        return (Page<ShopCategory>) ShopCategoryPage;
    }

    /**
     * 获取全部商品列表
     *
     * @return
     */
    @Override
    public List<ShopCategory> getAll() {
        List<ShopCategory> categories = this.list();
        return categories;
    }

    /**
     * 编辑商品类别
     *
     * @param category
     */
    @Override
    public void saveCategory(ShopCategory category) {
        this.saveOrUpdate(category);
    }

    /**
     * 删除商品类别
     *
     * @param id
     */
    @Override
    public void remove(Long id) {
        QueryWrapper<ShopGoods> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopGoods.COL_ID_CATEGORY, id);
        long goodsCount = goodsService.count(wrapper);
        if (goodsCount > 0) {
            throw new RuntimeException("删除错误");
        }
        this.removeById(id);
    }

    /**
     * 获取Banner
     *
     * @param idCategory
     * @return
     */
    @Override
    public List<Banner> getBanners(Long idCategory) {
        QueryWrapper<ShopCategoryBannerRel> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopCategoryBannerRel.COL_ID_CATEGORY, idCategory);
        List<ShopCategoryBannerRel> relList = categoryBannerRelService.list(wrapper);
        List<Banner> bannerList = Lists.newArrayList();
        relList.forEach(item -> {
            bannerList.add(item.getBanner());
        });
        return bannerList;
    }

    /**
     * 获取商品属性值
     *
     * @param idCategory
     * @return
     */
    @Override
    public List<ShopAttrKey> getAttrKeys(Long idCategory) {
        QueryWrapper<ShopAttrKey> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopAttrKey.COL_ID_CATEGORY, idCategory);
        List<ShopAttrKey> list = attrKeyService.list(wrapper);
        return list;
    }

    /**
     * 删除Banner
     *
     * @param idCategory
     * @param idBanner
     */
    @Override
    public void removeBanner(Long idCategory, Long idBanner) {
        QueryWrapper<ShopCategoryBannerRel> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopCategoryBannerRel.COL_ID_CATEGORY, idCategory);
        wrapper.eq(ShopCategoryBannerRel.COL_ID_BANNER, idBanner);
        ShopCategoryBannerRel rel = categoryBannerRelService.getOne(wrapper);
        if (rel != null) {
            categoryBannerRelService.removeById(rel);
        }
    }

    /**
     * 设置Banner
     *
     * @param idCategory
     * @param idBanner
     */
    @Override
    public String setBanner(Long idCategory, Long idBanner) {
        QueryWrapper<ShopCategoryBannerRel> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopCategoryBannerRel.COL_ID_CATEGORY, idCategory);
        wrapper.eq(ShopCategoryBannerRel.COL_ID_BANNER, idBanner);
        ShopCategoryBannerRel rel = categoryBannerRelService.getOne(wrapper);
        if (rel != null) {
            return null;
        }
        rel = new ShopCategoryBannerRel();
        rel.setIdCategory(idCategory);
        rel.setIdBanner(idBanner);
        categoryBannerRelService.save(rel);
        return null;
    }
}
