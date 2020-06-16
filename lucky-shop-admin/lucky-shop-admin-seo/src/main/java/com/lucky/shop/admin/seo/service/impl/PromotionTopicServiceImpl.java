package com.lucky.shop.admin.seo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.seo.domain.PromotionTopic;
import com.lucky.shop.admin.seo.mapper.PromotionTopicMapper;
import com.lucky.shop.admin.seo.service.PromotionTopicService;
import com.lucky.shop.common.core.factory.PageFactory;
import com.lucky.shop.common.core.utils.DateUtil;
import com.lucky.shop.common.core.utils.StringUtil;
import org.springframework.stereotype.Service;

/**
 * 专题
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 19:39
 */
@Service
public class PromotionTopicServiceImpl extends ServiceImpl<PromotionTopicMapper, PromotionTopic> implements PromotionTopicService {

    /**
     * 专题列表
     *
     * @param disabled
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public Page<PromotionTopic> list(Boolean disabled, String startDate, String endDate) {
        Page<PromotionTopic> page = new PageFactory<PromotionTopic>().defaultPage();
        QueryWrapper<PromotionTopic> wrapper = new QueryWrapper<>();
        if (StringUtil.isNotNullOrEmpty(disabled)) {
            wrapper.eq(PromotionTopic.COL_DISABLED, disabled);
        }
        if (StringUtil.isNotEmpty(startDate)) {
            wrapper.ge(PromotionTopic.COL_CREATE_TIME, DateUtil.parse(startDate, "yyyyMMddHHmmss"));
        }
        if (StringUtil.isNotEmpty(startDate)) {
            wrapper.le(PromotionTopic.COL_CREATE_TIME, DateUtil.parse(endDate, "yyyyMMddHHmmss"));
        }
        IPage<PromotionTopic> topicIPage = this.page(page, wrapper);
        return (Page<PromotionTopic>) topicIPage;
    }

    /**
     * 编辑专题
     *
     * @param topic
     */
    @Override
    public void saveTopic(PromotionTopic topic) {
        if (topic.getId() == null) {
            topic.setPv(0L);
            this.save(topic);
        } else {
            this.updateById(topic);
        }
    }

    /**
     * 删除专题
     *
     * @param id
     */
    @Override
    public void remove(Long id) {
        removeById(id);
    }

    /**
     * 禁用专题
     *
     * @param id
     * @param disabled
     */
    @Override
    public void changeIsOnSale(Long id, Boolean disabled) {
        PromotionTopic topic = getById(id);
        topic.setDisabled(disabled);
        updateById(topic);
    }
}
