package com.lucky.shop.common.factory;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.shop.common.constant.PageConstant;
import com.lucky.shop.common.utils.HttpUtil;
import com.lucky.shop.common.utils.StringUtil;

import javax.servlet.http.HttpServletRequest;


/**
 * 默认的分页参数创建
 *
 * @Create by Guo Huaijian
 * @Since 2020/6/3 12:33
 */
public class PageFactory<T> {

    public Page<T> defaultPage() {
        HttpServletRequest request = HttpUtil.getRequest();

        // 每页多少条数据
        int limit = 10;
        if (request.getParameter(PageConstant.SIZE) != null) {
            limit = Integer.valueOf(request.getParameter(PageConstant.SIZE));
        }

        // 当前页
        int pageNum = 1;
        if (request.getParameter(PageConstant.CURRENT) != null) {
            pageNum = Integer.valueOf(request.getParameter(PageConstant.CURRENT));
        }

        // 排序字段名称
        String sortName = request.getParameter(PageConstant.SORT_FIELD);

        // asc或desc(升序或降序)
        String order = request.getParameter(PageConstant.ORDER);
        if (StringUtil.isEmpty(sortName)) {
            Page<T> page = new Page<>(pageNum, limit);
            return page;
        } else {
            Page<T> page = new Page<>(pageNum, limit);
            if (PageConstant.ASC.equals(order)) {
                page.setAsc(sortName);
            } else {
                page.setDesc(sortName);
            }
            return page;
        }
    }
}
