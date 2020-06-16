package com.lucky.shop.admin.cms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.admin.cms.domain.CmsContacts;

/**
 * 邀约信息
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 14:42
 */
public interface CmsContactsService extends IService<CmsContacts> {

    /**
     * 邀约信息列表
     *
     * @param userName
     * @param mobile
     * @param startDate
     * @param endDate
     * @return
     */
    Page<CmsContacts> list(String userName, String mobile, String startDate, String endDate);

}
