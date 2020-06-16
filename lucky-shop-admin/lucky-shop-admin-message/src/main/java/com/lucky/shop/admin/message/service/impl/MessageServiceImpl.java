package com.lucky.shop.admin.message.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.message.domain.Message;
import com.lucky.shop.admin.message.mapper.MessageMapper;
import com.lucky.shop.admin.message.service.MessageService;
import com.lucky.shop.common.core.factory.PageFactory;
import com.lucky.shop.common.core.utils.DateUtil;
import com.lucky.shop.common.core.utils.StringUtil;
import org.springframework.stereotype.Service;

/**
 * 历史消息
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 18:58
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    /**
     * 消息列表
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public Page<Message> list(String startDate, String endDate) {
        Page<Message> page = new PageFactory<Message>().defaultPage();
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        if (StringUtil.isNotEmpty(startDate)) {
            wrapper.eq(Message.COL_CREATE_TIME, DateUtil.parse(startDate, "yyyyMMddHHmmss"));
        }
        if (StringUtil.isNotEmpty(endDate)) {
            wrapper.eq(Message.COL_CREATE_TIME, DateUtil.parse(endDate, "yyyyMMddHHmmss"));
        }
        IPage<Message> messageIPage = this.page(page, wrapper);
        return (Page<Message>) messageIPage;
    }

    /**
     * 清空所有历史消息
     */
    @Override
    public void clear() {
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        wrapper.isNotNull(Message.COL_ID);
        this.remove(wrapper);
    }
}
