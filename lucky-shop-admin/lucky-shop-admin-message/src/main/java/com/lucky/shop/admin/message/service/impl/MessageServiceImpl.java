package com.lucky.shop.admin.message.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.message.domain.Message;
import com.lucky.shop.admin.message.domain.MessageSender;
import com.lucky.shop.admin.message.domain.MessageTemplate;
import com.lucky.shop.admin.message.mapper.MessageMapper;
import com.lucky.shop.admin.message.service.*;
import com.lucky.shop.common.core.factory.PageFactory;
import com.lucky.shop.common.core.tool.SpringContextHolder;
import com.lucky.shop.common.core.utils.DateUtil;
import com.lucky.shop.common.core.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.text.StrSubstitutor;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.*;

/**
 * 历史消息
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 18:58
 */
@Service
@Slf4j
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Autowired
    private MessageTemplateService messageTemplateService;

    @Autowired
    private MessageSenderService messageSenderService;

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


    public void sendTplEmail(String tplCode, String from, String to, String cc, String title, Map<String, Object> dataMap) {
        MessageTemplate messageTemplate = messageTemplateService.findByCode(tplCode);
        String content = getContent(messageTemplate.getContent(), dataMap);
        sendEmailMessage(tplCode, from, to, cc, title, content, messageTemplate, null, null);
    }

    public void sendTplEmail(String tplCode, String from, String to, String cc, String title,
                             String attachmentFilename, InputStreamSource inputStreamSource,
                             Map<String, Object> dataMap) {
        MessageTemplate messageTemplate = messageTemplateService.findByCode(tplCode);
        String content = getContent(messageTemplate.getContent(), dataMap);
        sendEmailMessage(tplCode, from, to, cc, title, content, messageTemplate, attachmentFilename, inputStreamSource);
    }

    public void sendSimpleEmail(String tplCode, String from, String to, String cc, String title, String... args) {
        MessageTemplate messageTemplate = messageTemplateService.findByCode(tplCode);
        String content = getContent(messageTemplate.getContent(), args);
        sendEmailMessage(tplCode, from, to, cc, title, content, messageTemplate, null, null);
    }

    public void sendSms(String tplCode, String receiver, String... args) {
        MessageTemplate messageTemplate = messageTemplateService.findByCode(tplCode);
        String content = getContent(messageTemplate.getContent(), args);
        boolean isSuccess = false;
        try {
            isSuccess = this.sendSmsMessage(receiver, content, messageTemplate, args);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException();

        }
        saveMessage(0, tplCode, receiver, content, isSuccess);
    }

    private void sendEmailMessage(String tplCode, String from, String to, String cc, String title,
                                  String content, MessageTemplate messageTemplate,
                                  String attachmentFilename, InputStreamSource inputStreamSource) {
        try {
            EmailSender emailSender = getEmailSender(messageTemplate);
            boolean isSuccess = false;
            if (inputStreamSource != null) {
                isSuccess = emailSender.sendEmail(from, to, cc, title, content, attachmentFilename, inputStreamSource);
            } else {
                isSuccess = emailSender.sendEmail(from, to, cc, title, content);
            }
            saveMessage(1, tplCode, to, content, isSuccess);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            saveMessage(1, tplCode, to, content, false);
        }
    }

    private String getContent(String template, String... args) {
        List<Object> argList = new ArrayList<>();
        argList.add("");
        if (args != null) {
            Collections.addAll(argList, args);
        }
        String content = MessageFormat.format(template, Lang.collection2array(argList));
        return content;
    }

    private String getContent(String template, Map<String, Object> dataMap) {
        return StrSubstitutor.replace(template, dataMap);
    }

    private void saveMessage(Integer type, String tplCode, String receiver, String content, Boolean sendResult) {
        Message message = new Message();
        message.setType(type.toString());
        message.setTplCode(tplCode);
        message.setType("0");
        message.setState(sendResult ? "1" : "2");
        message.setReceiver(receiver);
        message.setCreateTime(new Date());
        message.setContent(content);
        this.save(message);

    }


    private boolean sendSmsMessage(String receiver, String content, MessageTemplate messageTemplate, String... args) throws Exception {
        String tplCode = getTpl(messageTemplate);
        SmsSender smsSender = getSmsSender(messageTemplate);
        log.info("receiver:{},content:{}\r\n,args:{}", receiver, content, Json.toJson(args));
        boolean success = false;
        String[] receivers = receiver.split(",|;", -1);
        for (String oneReceiver : receivers) {
            try {

                if (StringUtil.isNotEmpty(oneReceiver)) {
                    success = smsSender.sendSms(tplCode, oneReceiver, args, content);
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }

        return success;
    }

    private SmsSender getSmsSender(MessageTemplate messageTemplate) throws Exception {
        MessageSender messageSender = messageSenderService.getById(messageTemplate.getIdMessageSender());
        if (messageSender != null) {
            try {
                return SpringContextHolder.getBean(messageSender.getClassName());
            } catch (Exception e) {
                log.error("获取SmsService实现类失败", e);
                throw new Exception("smsService名称配置失败:" + messageSender.getClassName());
            }
        } else {
            throw new Exception("未配置运营商模版id");
        }
    }

    private EmailSender getEmailSender(MessageTemplate messageTemplate) throws Exception {
        MessageSender messageSender = messageSenderService.getById(messageTemplate.getIdMessageSender());
        if (messageSender != null) {
            try {
                return SpringContextHolder.getBean(messageSender.getClassName());
            } catch (Exception e) {
                log.error("获取SmsService实现类失败", e);
                throw new Exception("smsService名称配置失败:" + messageSender.getClassName());
            }
        } else {
            throw new Exception("未配置运营商模版id");
        }
    }

    private String getTpl(MessageTemplate messageTemplate) {
        MessageSender messageSender = messageSenderService.getById(messageTemplate.getIdMessageSender());

        if (messageSender != null && StringUtil.isNotEmpty(messageSender.getTplCode())) {
            return messageSender.getTplCode();
        } else {
            return null;
        }
    }
}
