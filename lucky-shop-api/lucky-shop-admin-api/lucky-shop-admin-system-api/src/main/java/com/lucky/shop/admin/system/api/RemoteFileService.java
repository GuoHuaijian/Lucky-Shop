package com.lucky.shop.admin.system.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.shop.admin.system.api.domain.SysFileInfo;
import com.lucky.shop.admin.system.api.factory.FileServiceFactory;
import com.lucky.shop.common.core.constant.ServiceNameConstants;
import com.lucky.shop.config.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 文件服务接口
 *
 * @Author Guo Huaijian
 * @Date 2020/6/5 18:52
 */
@FeignClient(value = ServiceNameConstants.LUCKY_SHOP_ADMIN_SYSTEM, path = "system/file", configuration =
        FeignRequestConfiguration.class, fallbackFactory = FileServiceFactory.class)
public interface RemoteFileService {

    /**
     * 根据模板创建excel文件
     *
     * @param template excel模板
     * @param fileName 导出的文件名称
     * @param data     excel中填充的数据
     * @return
     */
    @PostMapping("/createExcel/{template}/{fileName}")
    SysFileInfo createExcel(@PathVariable String template, @PathVariable String fileName,
                            @RequestBody Map<String, Object> data);

    /**
     * 查询文件信息列表
     *
     * @param originalFileName
     * @return
     */
    @GetMapping("list")
    Page<SysFileInfo> list(@RequestParam(required = false) String originalFileName);

    /**
     * 主键获取文件信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    SysFileInfo getOne(@PathVariable Long id);

    /**
     * 保存
     *
     * @param fileInfo
     */
    @PostMapping
    void save(@RequestBody SysFileInfo fileInfo);

    /**
     * 文件名获取文件信息
     *
     * @param realFileName
     * @return
     */
    @GetMapping("fileName/{realFileName}")
    SysFileInfo getByFileName(@PathVariable String realFileName);

}
