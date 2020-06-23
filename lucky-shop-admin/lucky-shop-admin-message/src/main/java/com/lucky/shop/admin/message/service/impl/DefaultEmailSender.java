package com.lucky.shop.admin.message.service.impl;

import com.lucky.shop.admin.message.service.EmailSender;
import com.lucky.shop.common.core.utils.StringUtil;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

/**
 * 默认的邮件发送服务类
 *
 * @Author Guo Huaijian
 * @Date 2020/6/22 17:14
 */
@Service
public class DefaultEmailSender implements EmailSender {

    @Resource
    private JavaMailSender javaMailSender;

    @Override
    public boolean sendEmail(String from, String to, String cc, String title, String content) {
        return sendEmail(from, to, cc, title, content, null, null);
    }

    @Override
    public boolean sendEmail(String from, String to, String cc, String title, String content, String attachmentFilename, InputStreamSource inputStreamSource) {
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            if (StringUtil.isNotEmpty(cc)) {
                helper.setCc(cc);
            }
            helper.setSubject(title);
            helper.setText(content, true);
            if (inputStreamSource != null) {
                helper.addAttachment(attachmentFilename, inputStreamSource);
            }
            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}

