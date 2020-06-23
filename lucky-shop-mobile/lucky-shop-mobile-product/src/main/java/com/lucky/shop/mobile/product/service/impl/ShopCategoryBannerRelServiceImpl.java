package com.lucky.shop.mobile.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.mobile.product.domain.ShopCategoryBannerRel;
import com.lucky.shop.mobile.product.mapper.ShopCategoryBannerRelMapper;
import com.lucky.shop.mobile.product.service.ShopCategoryBannerRelService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类别banner关联表
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 1:27
 */
@Service
public class ShopCategoryBannerRelServiceImpl extends ServiceImpl<ShopCategoryBannerRelMapper, ShopCategoryBannerRel> implements ShopCategoryBannerRelService {

    /**
     * 根据分类id查询列表
     *
     * @param idCategory
     * @return
     */
    @Override
    public List<ShopCategoryBannerRel> queryAll(Long idCategory) {
        QueryWrapper<ShopCategoryBannerRel> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopCategoryBannerRel.COL_ID_CATEGORY, idCategory);
        return this.list(wrapper);
    }
}
