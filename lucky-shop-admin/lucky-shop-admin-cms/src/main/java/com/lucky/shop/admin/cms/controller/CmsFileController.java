package com.lucky.shop.admin.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.shop.admin.system.api.RemoteFileService;
import com.lucky.shop.admin.system.api.domain.SysFileInfo;
import com.lucky.shop.common.core.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件信息列表
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 15:04
 */
@RestController
@RequestMapping("cms/fileMgr")
public class CmsFileController {

    @Autowired
    private RemoteFileService fileService;

    /**
     * 查询文件信息列表
     *
     * @param originalFileName
     * @return
     */
    @GetMapping(value = "/list")
//    @RequiresPermissions(value = {Permission.FILE})
    public ResponseResult list(@RequestParam(required = false) String originalFileName) {
        Page<SysFileInfo> page = fileService.list(originalFileName);
        return ResponseResult.success(page);
    }
}
