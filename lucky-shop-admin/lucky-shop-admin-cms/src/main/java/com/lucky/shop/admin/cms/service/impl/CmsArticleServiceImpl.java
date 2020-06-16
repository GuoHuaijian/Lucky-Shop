package com.lucky.shop.admin.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.cms.domain.CmsArticle;
import com.lucky.shop.admin.cms.mapper.CmsArticleMapper;
import com.lucky.shop.admin.cms.service.CmsArticleService;
import com.lucky.shop.common.core.factory.PageFactory;
import com.lucky.shop.common.core.utils.DateUtil;
import com.lucky.shop.common.core.utils.StringUtil;
import org.springframework.stereotype.Service;

/**
 * 文章
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 14:04
 */
@Service
public class CmsArticleServiceImpl extends ServiceImpl<CmsArticleMapper, CmsArticle> implements CmsArticleService {

    /**
     * 编辑文章
     *
     * @param article
     */
    @Override
    public void saveArticle(CmsArticle article) {
        if (article.getId() != null) {
            CmsArticle old = this.getById(article.getId());
            old.setAuthor(article.getAuthor());
            old.setContent(article.getContent());
            old.setIdChannel(article.getIdChannel());
            old.setImg(article.getImg());
            old.setTitle(article.getTitle());
            this.updateById(old);
        } else {
            this.save(article);
        }
    }

    /**
     * 删除文章
     *
     * @param id
     */
    @Override
    public void remove(Long id) {
        this.removeById(id);
    }

    /**
     * 根据id查询文章
     *
     * @param id
     * @return
     */
    @Override
    public CmsArticle get(Long id) {
        return this.getById(id);
    }

    /**
     * 根据条件查询文章列表
     *
     * @param title
     * @param author
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public Page<CmsArticle> list(String title, String author, String startDate, String endDate) {
        QueryWrapper<CmsArticle> wrapper = new QueryWrapper<>();
        Page<CmsArticle> page = new PageFactory<CmsArticle>().defaultPage();
        if (StringUtil.isNotEmpty(title)) {
            wrapper.like(CmsArticle.COL_TITLE, title);
        }
        if (StringUtil.isNotEmpty(author)) {
            wrapper.eq(CmsArticle.COL_AUTHOR, author);
        }
        if (StringUtil.isNotEmpty(startDate)) {
            wrapper.ge(CmsArticle.COL_CREATE_TIME, DateUtil.parse(startDate, "yyyyMMddHHmmss"));
        }
        if (StringUtil.isNotEmpty(endDate)) {
            wrapper.le(CmsArticle.COL_CREATE_TIME, DateUtil.parse(endDate, "yyyyMMddHHmmss"));
        }
        IPage<CmsArticle> articlePage = this.page(page, wrapper);
        return (Page<CmsArticle>) articlePage;
    }
}
