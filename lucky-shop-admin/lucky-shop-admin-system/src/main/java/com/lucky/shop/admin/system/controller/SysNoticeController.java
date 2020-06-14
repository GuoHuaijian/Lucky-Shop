package com.lucky.shop.admin.system.controller;

import com.lucky.shop.admin.system.domain.SysNotice;
import com.lucky.shop.admin.system.service.SysNoticeService;
import com.lucky.shop.common.core.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 通知
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 22:06
 */
@RestController
@RequestMapping("/notice")
public class SysNoticeController {

    @Autowired
    private SysNoticeService noticeService;

    /**
     * 获取通知列表
     *
     * @param condition
     * @return
     */
    @GetMapping(value = "/list")
    public ResponseResult list(String condition) {
        List<SysNotice> list = noticeService.list(condition);
        return ResponseResult.success(list);
    }
}
