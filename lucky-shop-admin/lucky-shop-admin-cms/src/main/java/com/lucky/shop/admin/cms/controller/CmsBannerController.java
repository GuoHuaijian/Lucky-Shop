package com.lucky.shop.admin.cms.controller;

import com.alibaba.fastjson.JSON;
import com.lucky.shop.admin.cms.domain.CmsBanner;
import com.lucky.shop.admin.cms.service.CmsBannerService;
import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.common.core.utils.StringUtil;
import com.lucky.shop.common.log.annotation.BussinessLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * banner管理
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 13:40
 */
@RestController
@RequestMapping("/banner")
public class CmsBannerController {

    @Autowired
    private CmsBannerService bannerService;

    /**
     * 编辑banner
     *
     * @param banner
     * @return
     */
    @PostMapping()
    @BussinessLog(value = "编辑banner", key = "title")
//    @RequiresPermissions(value = {Permission.BANNER_EDIT})
    public ResponseResult save(@ModelAttribute @Valid CmsBanner banner) {
        if(StringUtil.isNotEmpty(banner.getParam())){
            if(!JSON.isValid(banner.getParam())){
                return ResponseResult.error("参数必须为json格式");
            }
        }
        bannerService.saveBanner(banner);
        return ResponseResult.success();
    }

    /**
     * 删除banner
     *
     * @param id
     * @return
     */
    @DeleteMapping()
    @BussinessLog(value = "删除banner", key = "id")
//    @RequiresPermissions(value = {Permission.BANNER_DEL})
    public ResponseResult remove(Long id) {
        bannerService.remove(id);
        return ResponseResult.success();
    }

    /**
     * banner列表
     *
     * @param title
     * @return
     */
    @GetMapping(value = "/list")
//    @RequiresPermissions(value = {Permission.BANNER})
    public ResponseResult list(@RequestParam(required = false) String title) {
        List<CmsBanner> list = bannerService.list(title);
        return ResponseResult.success(list);
    }
}
