package com.lucky.shop.admin.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.shop.admin.cms.base.BaseController;
import com.lucky.shop.admin.cms.domain.CmsArticle;
import com.lucky.shop.admin.cms.service.CmsArticleService;
import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.common.log.annotation.BussinessLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

/**
 * 文章
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 14:05
 */
@RestController
@RequestMapping("cms/article")
public class CmsArticleController extends BaseController {

    @Autowired
    private CmsArticleService articleService;

    /**
     * 编辑文章
     *
     * @return
     */
    @PostMapping()
    @BussinessLog(value = "编辑文章", key = "name")
//    @RequiresPermissions(value = {Permission.ARTICLE_EDIT})
    public ResponseResult save() {
        CmsArticle article = getFromJson(CmsArticle.class);
        articleService.saveArticle(article);
        return ResponseResult.success();
    }

    /**
     * 删除文章
     *
     * @param id
     * @return
     */
    @DeleteMapping()
    @BussinessLog(value = "删除文章", key = "id")
//    @RequiresPermissions(value = {Permission.ARTICLE_DEL})
    public ResponseResult remove(Long id) {
        articleService.remove(id);
        return ResponseResult.success();
    }

    /**
     * 根据id查询文章
     *
     * @param id
     * @return
     */
    @GetMapping()
//    @RequiresPermissions(value = {Permission.ARTICLE})
    public ResponseResult get(@Param("id") Long id) {
        CmsArticle article = articleService.get(id);
        return ResponseResult.success(article);
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
    @GetMapping(value = "/list")
//    @RequiresPermissions(value = {Permission.ARTICLE})
    public ResponseResult list(@RequestParam(required = false) String title,
                               @RequestParam(required = false) String author,
                               @RequestParam(required = false) String startDate,
                               @RequestParam(required = false) String endDate
    ) {
        Page<CmsArticle> page = articleService.list(title, author, startDate, endDate);
        return ResponseResult.success(page);
    }
}
