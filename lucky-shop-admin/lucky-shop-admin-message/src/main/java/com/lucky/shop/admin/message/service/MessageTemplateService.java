package com.lucky.shop.admin.message.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.admin.message.domain.MessageTemplate;

/**
 * 消息模板
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 19:25
 */
public interface MessageTemplateService extends IService<MessageTemplate>{

    /**
     * 消息模板列表
     *
     * @return
     */
    Page<MessageTemplate> templateList();

    /**
     * 编辑消息模板
     *
     * @param messageTemplate
     */
     void saveTemplate(MessageTemplate messageTemplate);

    /**
     * 删除消息模板
     *
     * @param id
     */
     void remove(Long id);

}
