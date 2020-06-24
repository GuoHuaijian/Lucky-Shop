package com.lucky.shop.admin.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.shop.admin.system.domain.SysFileInfo;
import com.lucky.shop.admin.system.service.SysFileInfoService;
import com.lucky.shop.admin.system.service.impl.FileService;
import com.lucky.shop.common.core.factory.PageFactory;
import com.lucky.shop.common.core.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 文件
 *
 * @Author Guo Huaijian
 * @Date 2020/6/5 18:58
 */
@RestController
@RequestMapping("system/file")
public class FileServiceController {

    @Autowired
    private FileService fileService;

    @Autowired
    private SysFileInfoService fileInfoService;

    /**
     * 根据模板创建excel文件
     *
     * @param template excel模板
     * @param fileName 导出的文件名称
     * @param data     excel中填充的数据
     * @return
     */
    @PostMapping("/createExcel/{template}/{fileName}")
    public SysFileInfo createExcel(@PathVariable String template, @PathVariable String fileName,
                                   @RequestBody Map<String, Object> data) {
        SysFileInfo fileInfo = fileService.createExcel(template, fileName, data);
        return fileInfo;
    }

    /**
     * 查询文件信息列表
     *
     * @param originalFileName
     * @return
     */
    @GetMapping("list")
    public Page<SysFileInfo> list(@RequestParam(required = false) String originalFileName) {
        Page<SysFileInfo> page = new PageFactory<SysFileInfo>().defaultPage();
        QueryWrapper<SysFileInfo> wrapper = new QueryWrapper<>();
        if (StringUtil.isNotEmpty(originalFileName)) {
            wrapper.like(SysFileInfo.COL_ORIGINAL_FILE_NAME, originalFileName);
        }
        IPage<SysFileInfo> fileInfoIPage = fileInfoService.page(page, wrapper);
        return (Page<SysFileInfo>) fileInfoIPage;
    }

    /**
     * 主键获取文件信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public SysFileInfo getOne(@PathVariable Long id) {
        return fileInfoService.getById(id);
    }

    /**
     * 保存
     *
     * @param fileInfo
     */
    @PostMapping
    public void save(@RequestBody SysFileInfo fileInfo) {
        fileInfoService.save(fileInfo);
    }

    /**
     * 文件名获取文件信息
     *
     * @param realFileName
     * @return
     */
    @GetMapping("fileName/{realFileName}")
    public SysFileInfo getByFileName(@PathVariable String realFileName) {
        return fileService.getByName(realFileName);
    }
}
