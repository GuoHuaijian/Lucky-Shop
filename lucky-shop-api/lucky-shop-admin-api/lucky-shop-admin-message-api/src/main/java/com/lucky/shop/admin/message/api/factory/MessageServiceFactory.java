package com.lucky.shop.admin.message.api.factory;

import com.lucky.shop.admin.message.api.RemoteMessageService;
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
public class MessageServiceFactory implements RemoteMessageService {

    @Override
    public void sendSms(String tplCode, String receiver, String... args) {
        log.error("登录日志服务调用失败:{}");
    }
}
