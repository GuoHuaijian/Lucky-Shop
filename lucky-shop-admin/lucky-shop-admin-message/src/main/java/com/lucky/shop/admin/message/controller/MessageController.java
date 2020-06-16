package com.lucky.shop.admin.message.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.shop.admin.message.domain.Message;
import com.lucky.shop.admin.message.service.MessageService;
import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.common.log.annotation.BussinessLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 历史消息
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 18:59
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * 消息列表
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping(value = "/list")
//    @RequiresPermissions(value = {Permission.MSG})
    public ResponseResult list(@RequestParam(required = false) String startDate,
                               @RequestParam(required = false) String endDate) {
        Page<Message> list = messageService.list(startDate, endDate);
        return ResponseResult.success(list);
    }

    /**
     * 清空所有历史消息
     *
     * @return
     */
    @DeleteMapping()
    @BussinessLog(value = "清空所有历史消息")
//    @RequiresPermissions(value = {Permission.MSG_CLEAR})
    public ResponseResult clear() {
        messageService.clear();
        return ResponseResult.success();
    }
}
