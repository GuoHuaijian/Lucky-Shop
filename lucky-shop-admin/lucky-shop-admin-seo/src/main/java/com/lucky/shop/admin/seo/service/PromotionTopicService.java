package com.lucky.shop.admin.seo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.admin.seo.domain.PromotionTopic;

/**
 * 专题
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 19:39
 */
public interface PromotionTopicService extends IService<PromotionTopic>{

    /**
     * 专题列表
     *
     * @param disabled
     * @param startDate
     * @param endDate
     * @return
     */
    Page<PromotionTopic> list(Boolean disabled, String startDate, String endDate);

    /**
     * 编辑专题
     *
     * @param topic
     */
     void saveTopic(PromotionTopic topic);

    /**
     * 删除专题
     *
     * @param id
     */
     void remove(Long id);

    /**
     * 禁用专题
     *
     * @param id
     * @param disabled
     */
     void changeIsOnSale( Long id,  Boolean disabled);

}
