package com.lucky.shop.admin.message.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.admin.message.domain.Message;

/**
 * 历史消息
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 18:58
 */
public interface MessageService extends IService<Message> {


    /**
     * 消息列表
     *
     * @param startDate
     * @param endDate
     * @return
     */
    Page<Message> list(String startDate, String endDate);

    /**
     * 清空所有历史消息
     */
    void clear();

}
