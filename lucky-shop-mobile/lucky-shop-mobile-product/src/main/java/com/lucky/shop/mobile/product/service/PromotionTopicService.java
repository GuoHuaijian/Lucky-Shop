package com.lucky.shop.mobile.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.mobile.product.domain.PromotionTopic;

import java.util.List;

/**
 * 专题
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 2:24
 */
public interface PromotionTopicService extends IService<PromotionTopic> {

    /**
     * 专题列表
     *
     * @return
     */
    List<PromotionTopic> topicList();

    /**
     * 查询专题
     *
     * @param id
     * @return
     */
    PromotionTopic get(Long id);

}
