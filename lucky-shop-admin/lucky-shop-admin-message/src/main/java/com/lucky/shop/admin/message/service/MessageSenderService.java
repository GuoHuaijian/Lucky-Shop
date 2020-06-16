package com.lucky.shop.admin.message.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.admin.message.domain.MessageSender;

import java.util.List;

/**
 * 消息发送者
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 19:10
 */
public interface MessageSenderService extends IService<MessageSender> {

    /**
     * 消息发送者列表
     *
     * @return
     */
    Page<MessageSender> senderList();

    /**
     * 消息发送者全部列表
     *
     * @return
     */
    List<MessageSender> queryAll();

    /**
     * 编辑消息发送者
     *
     * @param messageSender
     */
    void saveSender(MessageSender messageSender);

    /**
     * 删除消息发送者
     *
     * @param id
     */
    void remove(Long id);

}
