package com.lucky.shop.admin.system.controller;

import com.lucky.shop.admin.system.domain.SysFileInfo;
import com.lucky.shop.admin.system.service.impl.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 文件
 * @Author Guo Huaijian
 * @Date 2020/6/5 18:58
 */
@RestController
@RequestMapping("/file")
public class FileServiceController {

    @Autowired
    private FileService fileService;

    /**
     * 根据模板创建excel文件
     * @param template excel模板
     * @param fileName 导出的文件名称
     * @param data  excel中填充的数据
     * @return
     */
    @PostMapping("/createExcel/{template}/{fileName}")
    public SysFileInfo createExcel(@PathVariable String template, @PathVariable String fileName,
                                   @RequestBody Map<String, Object> data){
        SysFileInfo fileInfo = fileService.createExcel(template, fileName, data);
        return fileInfo;
    }
}
