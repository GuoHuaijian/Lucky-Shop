package com.lucky.shop.mobile.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.common.core.utils.Lists;
import com.lucky.shop.mobile.product.domain.CmsBanner;
import com.lucky.shop.mobile.product.domain.ShopCategory;
import com.lucky.shop.mobile.product.domain.ShopCategoryBannerRel;
import com.lucky.shop.mobile.product.mapper.ShopCategoryMapper;
import com.lucky.shop.mobile.product.service.ShopCategoryBannerRelService;
import com.lucky.shop.mobile.product.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品类别
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 1:27
 */
@Service
public class ShopCategoryServiceImpl extends ServiceImpl<ShopCategoryMapper, ShopCategory> implements ShopCategoryService {

    @Autowired
    private ShopCategoryBannerRelService categoryBannerRelService;

    /**
     * 获取分类列表
     *
     * @return
     */
    @Override
    public List<ShopCategory> categoryList() {
        List<ShopCategory> list = this.list();
        list.forEach(item -> {
            List<ShopCategoryBannerRel> relList = categoryBannerRelService.queryAll(item.getId());
            List<CmsBanner> bannerList = Lists.newArrayList();
            relList.forEach(relItem -> {
                bannerList.add(relItem.getBanner());
            });

            item.setBannerList(bannerList);
        });
        return list;
    }
}
