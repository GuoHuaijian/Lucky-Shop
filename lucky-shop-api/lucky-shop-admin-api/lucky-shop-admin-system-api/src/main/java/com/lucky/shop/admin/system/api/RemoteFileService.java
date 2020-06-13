package com.lucky.shop.admin.system.api;

import com.lucky.shop.admin.system.api.domain.SysFileInfo;
import com.lucky.shop.admin.system.api.factory.FileServiceFactory;
import com.lucky.shop.common.core.constant.ServiceNameConstants;
import com.lucky.shop.config.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * 文件服务接口
 *
 * @Author Guo Huaijian
 * @Date 2020/6/5 18:52
 */
@FeignClient(value = ServiceNameConstants.LUCKY_SHOP_ADMIN_SYSTEM, path = "file", configuration =
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

}
