package com.lucky.shop.admin.message.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.shop.admin.message.domain.MessageSender;
import com.lucky.shop.admin.message.service.MessageSenderService;
import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.common.log.annotation.BussinessLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 消息发送者
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 19:11
 */
@RestController
@RequestMapping("message/sender")
public class MessageSenderController {

    @Autowired
    private MessageSenderService messageSenderService;

    /**
     * 消息发送者列表
     *
     * @return
     */
    @GetMapping(value = "/list")
//    @RequiresPermissions(value = {Permission.MSG_SENDER})
    public ResponseResult list() {
        Page<MessageSender> senderPage = messageSenderService.senderList();
        return ResponseResult.success(senderPage);
    }

    /**
     * 消息发送者全部列表
     *
     * @return
     */
    @GetMapping(value = "/queryAll")
//    @RequiresPermissions(value = {Permission.MSG_SENDER})
    public ResponseResult queryAll() {
        return ResponseResult.success(messageSenderService.queryAll());
    }

    /**
     * 编辑消息发送者
     *
     * @param messageSender
     * @return
     */
    @PostMapping()
    @BussinessLog(value = "编辑消息发送者", key = "name")
//    @RequiresPermissions(value = {Permission.MSG_SENDER_EDIT})
    public ResponseResult save(@ModelAttribute @Valid MessageSender messageSender) {
        messageSenderService.saveSender(messageSender);
        return ResponseResult.success();
    }

    /**
     * 删除消息发送者
     *
     * @param id
     * @return
     */
    @DeleteMapping()
    @BussinessLog(value = "删除消息发送者", key = "id")
//    @RequiresPermissions(value = {Permission.MSG_SENDER_DEL})
    public ResponseResult remove(Long id) {
        try {
            messageSenderService.remove(id);
            return ResponseResult.success();
        } catch (Exception e) {
            return ResponseResult.error(e.getMessage());
        }
    }
}
