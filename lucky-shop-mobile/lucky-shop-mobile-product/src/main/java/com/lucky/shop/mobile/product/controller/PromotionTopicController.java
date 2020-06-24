package com.lucky.shop.mobile.product.controller;

import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.mobile.product.domain.PromotionTopic;
import com.lucky.shop.mobile.product.service.PromotionTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 专题
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 2:24
 */
@RestController
@RequestMapping("mobile/product/topic")
public class PromotionTopicController {

    @Autowired
    private PromotionTopicService topicService;

    /**
     * 专题列表
     *
     * @return
     */
    @GetMapping(value = "/list")
    public ResponseResult list() {
        List<PromotionTopic> promotionTopics = topicService.topicList();
        return ResponseResult.success(promotionTopics);
    }

    /**
     * 查询专题
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseResult get(@PathVariable("id") Long id) {
        PromotionTopic topic = topicService.get(id);
        return ResponseResult.success(topic);
    }
}
