package com.lucky.shop.admin.message.service;

/**
 * @Author Guo Huaijian
 * @Date 2020/6/22 17:25
 */
public interface SmsSender {

    /**
     * 发送短信，如果内容content不为空，则直接发送内容
     * @param tplCode 短信运营商模板号码
     * @param receiver
     * @param args
     * @param content
     * @return
     */
    boolean sendSms(String tplCode, String receiver, String[] args, String content);
}
