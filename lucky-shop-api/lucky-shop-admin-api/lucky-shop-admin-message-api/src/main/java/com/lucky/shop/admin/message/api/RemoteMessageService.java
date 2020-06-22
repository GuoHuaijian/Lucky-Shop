package com.lucky.shop.admin.message.api;

import com.lucky.shop.admin.message.api.factory.MessageServiceFactory;
import com.lucky.shop.common.core.constant.ServiceNameConstants;
import com.lucky.shop.config.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 消息服务接口
 *
 * @Author Guo Huaijian
 * @Date 2020/6/22 17:43
 */
@FeignClient(value = ServiceNameConstants.LUCKY_SHOP_ADMIN_MESSAGE, path = "message", configuration =
        FeignRequestConfiguration.class, fallbackFactory = MessageServiceFactory.class)
public interface RemoteMessageService {

    /**
     * 发送消息
     *
     * @param tplCode
     * @param receiver
     * @param args
     */
    @PostMapping("sendSms")
    void sendSms(String tplCode, String receiver, String... args);
}
