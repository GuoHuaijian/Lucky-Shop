package com.lucky.shop.admin.cms.controller;

import com.lucky.shop.admin.cms.domain.CmsChannel;
import com.lucky.shop.admin.cms.service.CmsChannelService;
import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.common.log.annotation.BussinessLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 文章栏目
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 14:33
 */
@RestController
@RequestMapping("cms/channel")
public class CmsChannelController {

    @Autowired
    private CmsChannelService channelService;

    /**
     * 编辑栏目
     *
     * @param channel
     * @return
     */
    @PostMapping()
    @BussinessLog(value = "编辑栏目", key = "name")
//    @RequiresPermissions(value = {Permission.CHANNEL_EDIT})
    public ResponseResult save(@ModelAttribute @Valid CmsChannel channel) {
        channelService.saveChannel(channel);
        return ResponseResult.success();
    }

    /**
     * 删除栏目
     *
     * @param id
     * @return
     */
    @DeleteMapping()
    @BussinessLog(value = "删除栏目", key = "id")
//    @RequiresPermissions(value = {Permission.CHANNEL_DEL})
    public ResponseResult remove(Long id) {
        channelService.remove(id);
        return ResponseResult.success();
    }

    /**
     * 栏目列表
     *
     * @return
     */
    @GetMapping(value = "/list")
//    @RequiresPermissions(value = {Permission.CHANNEL})
    public ResponseResult list() {
        List<CmsChannel> list = channelService.channelList();
        return ResponseResult.success(list);
    }
}
