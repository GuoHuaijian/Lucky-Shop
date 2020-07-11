package com.lucky.shop.admin.mall.api.factory;

import com.lucky.shop.admin.mall.api.RemoteDashboardService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 首页服务降级处理
 *
 * @Author Guo Huaijian
 * @Date 2020/6/22 15:13
 */
@Component
@Slf4j
public class DashboardServiceFactory implements FallbackFactory<RemoteDashboardService> {

//    @Override
//    public Map get() {
//        log.error("首页服务调用失败:{}");
//        return null;
//    }

    @Override
    public RemoteDashboardService create(Throwable throwable) {
        log.error("首页服务调用失败:{}"+throwable.getMessage());
        return new RemoteDashboardService() {
            @Override
            public Map get() {
                return null;
            }
        };
    }
}
