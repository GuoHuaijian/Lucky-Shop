package com.lucky.shop.admin.system.api.factory;

import com.lucky.shop.admin.system.api.domain.SysFileInfo;
import com.lucky.shop.admin.system.api.RemoteFileService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 文件服务降级处理
 *
 * @Author Guo Huaijian
 * @Date 2020/6/5 19:16
 */
@Component
@Slf4j
public class FileServiceFactory implements FallbackFactory<RemoteFileService> {
    @Override
    public RemoteFileService create(Throwable throwable) {
        log.error("文件服务调用失败:{}", throwable.getMessage());
        return new RemoteFileService() {
            @Override
            public SysFileInfo createExcel(String template, String fileName, Map<String, Object> data) {
                return null;
            }
        };
    }
}
