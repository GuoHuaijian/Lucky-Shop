package com.lucky.shop.admin.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.admin.cms.domain.CmsBanner;

import java.util.List;

/**
 * banner管理
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 13:38
 */
public interface CmsBannerService extends IService<CmsBanner> {

    /**
     * 编辑banner
     *
     * @param banner
     */
     void saveBanner(CmsBanner banner);

    /**
     * 删除banner
     *
     * @param id
     */
    void remove(Long id);

    /**
     * banner列表
     *
     * @param title
     * @return
     */
     List<CmsBanner> list(String title) ;

}
