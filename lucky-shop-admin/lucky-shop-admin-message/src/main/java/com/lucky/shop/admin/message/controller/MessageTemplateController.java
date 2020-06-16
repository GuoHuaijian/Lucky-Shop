package com.lucky.shop.admin.message.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.shop.admin.message.domain.MessageTemplate;
import com.lucky.shop.admin.message.service.MessageTemplateService;
import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.common.log.annotation.BussinessLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 消息模板
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 19:32
 */
@RestController
@RequestMapping("/message/template")
public class MessageTemplateController {

    @Autowired
    private MessageTemplateService messageTemplateService;

    /**
     * 消息模板列表
     *
     * @return
     */
    @GetMapping(value = "/list")
//    @RequiresPermissions(value = {Permission.MSG_TPL})
    public ResponseResult list() {
        Page<MessageTemplate> page = messageTemplateService.templateList();
        return ResponseResult.success(page);
    }

    /**
     * 编辑消息模板
     *
     * @param messageTemplate
     * @return
     */
    @PostMapping()
    @BussinessLog(value = "编辑消息模板", key = "name")
//    @RequiresPermissions(value = {Permission.MSG_TPL_EDIT})
    public ResponseResult save(@ModelAttribute @Valid MessageTemplate messageTemplate) {
        messageTemplateService.saveTemplate(messageTemplate);
        return ResponseResult.success();
    }

    /**
     * 删除消息模板
     *
     * @param id
     * @return
     */
    @DeleteMapping()
    @BussinessLog(value = "删除消息模板", key = "id")
//    @RequiresPermissions(value = {Permission.MSG_TPL_DEL})
    public ResponseResult remove(Long id) {
        if (id == null) {
            throw new RuntimeException();
        }
        messageTemplateService.remove(id);
        return ResponseResult.success();
    }
}
