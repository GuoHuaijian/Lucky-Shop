package com.lucky.shop.admin.message.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.message.domain.MessageTemplate;
import com.lucky.shop.admin.message.mapper.MessageTemplateMapper;
import com.lucky.shop.admin.message.service.MessageTemplateService;
import com.lucky.shop.common.core.factory.PageFactory;
import org.springframework.stereotype.Service;

/**
 * 消息模板
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 19:25
 */
@Service
public class MessageTemplateServiceImpl extends ServiceImpl<MessageTemplateMapper, MessageTemplate> implements MessageTemplateService{

    /**
     * 消息模板列表
     *
     * @return
     */
    @Override
    public Page<MessageTemplate> templateList() {
        Page<MessageTemplate> page = new PageFactory<MessageTemplate>().defaultPage();
        IPage<MessageTemplate> templateIPage = this.page(page);
        return (Page<MessageTemplate>) templateIPage;
    }

    /**
     * 编辑消息模板
     *
     * @param messageTemplate
     */
    @Override
    public void saveTemplate(MessageTemplate messageTemplate) {
        if(messageTemplate.getId()==null){
            this.save(messageTemplate);
        }else {
            this.updateById(messageTemplate);
        }
    }

    /**
     * 删除消息模板
     *
     * @param id
     */
    @Override
    public void remove(Long id) {
        this.removeById(id);
    }
}
