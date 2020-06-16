package com.lucky.shop.admin.seo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.shop.admin.seo.domain.PromotionTopic;
import com.lucky.shop.admin.seo.service.PromotionTopicService;
import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.common.core.utils.StringUtil;
import com.lucky.shop.common.log.annotation.BussinessLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 专题
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 19:41
 */
@RestController
@RequestMapping("seo/promotion/topic")
public class PromotionTopicController {

    @Autowired
    private PromotionTopicService topicService;

    /**
     * 专题列表
     *
     * @param disabled
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping(value = "/list")
    public ResponseResult list(@RequestParam(value = "disabled", required = false) Boolean disabled,
                               @RequestParam(value = "startDate", required = false) String startDate,
                               @RequestParam(value = "endDate", required = false) String endDate) {
        Page<PromotionTopic> page = topicService.list(disabled, startDate, endDate);
        return ResponseResult.success(page);
    }

    /**
     * 编辑专题
     *
     * @param topic
     * @return
     */
    @PostMapping()
    @BussinessLog(value = "编辑专题", key = "name")
//    @RequiresPermissions(value = {Permission.TOPIC_EDIT})
    public ResponseResult save(@ModelAttribute PromotionTopic topic) {
        topicService.saveTopic(topic);
        return ResponseResult.success();
    }

    /**
     * 删除专题
     *
     * @param id
     * @return
     */
    @DeleteMapping()
    @BussinessLog(value = "删除专题", key = "id")
//    @RequiresPermissions(value = {Permission.TOPIC_DEL})
    public ResponseResult remove(Long id) {
        if (StringUtil.isEmpty(id)) {
            throw new RuntimeException();
        }
        topicService.remove(id);
        return ResponseResult.success();
    }

    /**
     * 禁用专题
     *
     * @param id
     * @param disabled
     * @return
     */
    @PostMapping(value = "/changeDisabled")
//    @RequiresPermissions(value = {Permission.TOPIC_EDIT})
    public ResponseResult changeIsOnSale(@RequestParam("id") Long id, @RequestParam("disabled") Boolean disabled) {
        if (id == null) {
            throw new RuntimeException();
        }
        topicService.changeIsOnSale(id, disabled);
        return ResponseResult.success();
    }
}
