package com.lucky.shop.admin.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.cms.domain.CmsContacts;
import com.lucky.shop.admin.cms.mapper.CmsContactsMapper;
import com.lucky.shop.admin.cms.service.CmsContactsService;
import com.lucky.shop.common.core.factory.PageFactory;
import com.lucky.shop.common.core.utils.DateUtil;
import com.lucky.shop.common.core.utils.StringUtil;
import org.springframework.stereotype.Service;

/**
 * 邀约信息
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 14:42
 */
@Service
public class CmsContactsServiceImpl extends ServiceImpl<CmsContactsMapper, CmsContacts> implements CmsContactsService{

    /**
     * 邀约信息列表
     *
     * @param userName
     * @param mobile
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public Page<CmsContacts> list(String userName, String mobile, String startDate, String endDate) {
        Page<CmsContacts> page = new PageFactory<CmsContacts>().defaultPage();
        QueryWrapper<CmsContacts> wrapper = new QueryWrapper<>();
        if (StringUtil.isNotEmpty(startDate)){
            wrapper.ge(CmsContacts.COL_CREATE_TIME, DateUtil.parse(startDate,"yyyyMMddHHmmss"));
        }
        if (StringUtil.isNotEmpty(endDate)){
            wrapper.le(CmsContacts.COL_CREATE_TIME, DateUtil.parse(endDate,"yyyyMMddHHmmss"));
        }
        if (StringUtil.isNotEmpty(userName)){
            wrapper.eq(CmsContacts.COL_USER_NAME,userName);
        }
        if (StringUtil.isNotEmpty(mobile)){
            wrapper.eq(CmsContacts.COL_MOBILE,mobile);
        }
        IPage<CmsContacts> contactsIPage = this.page(page, wrapper);
        return (Page<CmsContacts>) contactsIPage;
    }
}
