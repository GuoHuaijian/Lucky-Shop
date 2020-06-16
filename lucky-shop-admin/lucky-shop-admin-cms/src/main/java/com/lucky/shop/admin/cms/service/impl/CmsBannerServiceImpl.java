package com.lucky.shop.admin.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.cms.domain.CmsBanner;
import com.lucky.shop.admin.cms.mapper.CmsBannerMapper;
import com.lucky.shop.admin.cms.service.CmsBannerService;
import com.lucky.shop.common.core.utils.StringUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * banner管理
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 13:38
 */
@Service
public class CmsBannerServiceImpl extends ServiceImpl<CmsBannerMapper, CmsBanner> implements CmsBannerService {

    /**
     * 编辑banner
     *
     * @param banner
     */
    @Override
    public void saveBanner(CmsBanner banner) {
        if (banner.getId() == null) {
            this.save(banner);
        } else {
            this.updateById(banner);
        }
    }

    /**
     * 删除banner
     *
     * @param id
     */
    @Override
    public void remove(Long id) {
        this.removeById(id);
    }

    /**
     * banner列表
     *
     * @param title
     * @return
     */
    @Override
    public List<CmsBanner> list(String title) {
        List<CmsBanner> list;
        if (StringUtil.isNotEmpty(title)) {
            QueryWrapper<CmsBanner> wrapper = new QueryWrapper<>();
            wrapper.like(CmsBanner.COL_TITLE, title);
            list = this.list(wrapper);
            return list;
        } else {
            list = this.list();
            return list;
        }
    }
}
