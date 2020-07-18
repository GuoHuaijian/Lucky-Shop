package com.lucky.shop.admin.message.api.factory;

import com.lucky.shop.admin.message.api.RemoteMessageService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 消息服务降级处理
 *
 * @Author Guo Huaijian
 * @Date 2020/6/22 17:45
 */
@Component
@Slf4j
public class MessageServiceFactory implements FallbackFactory<RemoteMessageService> {

    @Override
    public RemoteMessageService create(Throwable throwable) {
        log.error("消息服务调用失败:{}");
        return new RemoteMessageService() {
            @Override
            public void sendSms(String tplCode, String receiver, String... args) {

            }
        };
    }
}
