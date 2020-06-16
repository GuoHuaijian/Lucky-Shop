package com.lucky.shop.admin.message.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.message.domain.MessageSender;
import com.lucky.shop.admin.message.mapper.MessageSenderMapper;
import com.lucky.shop.admin.message.service.MessageSenderService;
import com.lucky.shop.common.core.factory.PageFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 消息发送者
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 19:10
 */
@Service
public class MessageSenderServiceImpl extends ServiceImpl<MessageSenderMapper, MessageSender> implements MessageSenderService{

    /**
     * 消息发送者列表
     *
     * @return
     */
    @Override
    public Page<MessageSender> senderList() {
        Page<MessageSender> page = new PageFactory<MessageSender>().defaultPage();
        IPage<MessageSender> SenderPage = this.page(page);
        return (Page<MessageSender>) SenderPage;
    }

    /**
     * 消息发送者全部列表
     *
     * @return
     */
    @Override
    public List<MessageSender> queryAll() {
        return this.list();
    }

    /**
     * 编辑消息发送者
     *
     * @param messageSender
     */
    @Override
    public void saveSender(MessageSender messageSender) {
        if(messageSender.getId()!=null){
            MessageSender old = this.getById(messageSender.getId());
        }
        this.save(messageSender);
    }

    /**
     * 删除消息发送者
     *
     * @param id
     */
    @Override
    public void remove(Long id) {
        this.removeById(id);
    }
}
