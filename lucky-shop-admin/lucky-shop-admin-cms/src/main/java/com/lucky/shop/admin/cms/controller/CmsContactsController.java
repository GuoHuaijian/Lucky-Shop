package com.lucky.shop.admin.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.shop.admin.cms.domain.CmsContacts;
import com.lucky.shop.admin.cms.service.CmsContactsService;
import com.lucky.shop.common.core.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 邀约信息
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 14:50
 */
@RestController
@RequestMapping("/contacts")
public class CmsContactsController {

    @Autowired
    private CmsContactsService contactsService;

    /**
     * 邀约信息列表
     *
     * @param userName
     * @param mobile
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping(value = "/list")
//    @RequiresPermissions(value = {Permission.CONTACTS})
    public ResponseResult list(@RequestParam(required = false) String userName,
                               @RequestParam(required = false) String mobile,
                               @RequestParam(required = false) String startDate,
                               @RequestParam(required = false) String endDate

    ) {
        Page<CmsContacts> page = contactsService.list(userName, mobile, startDate, endDate);
        return ResponseResult.success(page);
    }
}
