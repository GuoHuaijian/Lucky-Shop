package com.lucky.shop.admin.cms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.admin.cms.domain.CmsArticle;

/**
 * 文章
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 14:04
 */
public interface CmsArticleService extends IService<CmsArticle>{

    /**
     * 编辑文章
     *
     * @param article
     */
     void saveArticle(CmsArticle article);

    /**
     * 删除文章
     *
     * @param id
     * @return
     */
     void remove(Long id);

    /**
     * 根据id查询文章
     *
     * @param id
     * @return
     */
     CmsArticle get( Long id);

    /**
     * 根据条件查询文章列表
     *
     * @param title
     * @param author
     * @param startDate
     * @param endDate
     * @return
     */
    Page<CmsArticle> list(String title, String author, String startDate, String endDate);

}
