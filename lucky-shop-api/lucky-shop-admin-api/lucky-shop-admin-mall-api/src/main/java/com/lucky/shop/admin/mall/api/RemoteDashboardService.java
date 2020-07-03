package com.lucky.shop.admin.mall.api;

import com.lucky.shop.admin.mall.api.factory.DashboardServiceFactory;
import com.lucky.shop.common.core.constant.ServiceNameConstants;
import com.lucky.shop.config.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * 首页服务接口
 *
 * @Author Guo Huaijian
 * @Date 2020/6/22 15:10
 */
@FeignClient(value = ServiceNameConstants.LUCKY_SHOP_ADMIN_MALL, path = "mall/dashboard", configuration =
        FeignRequestConfiguration.class, fallbackFactory = DashboardServiceFactory.class)
public interface RemoteDashboardService {

    /**
     * 获取首页内容
     *
     * @return
     */
    @GetMapping()
    Map get();
}
