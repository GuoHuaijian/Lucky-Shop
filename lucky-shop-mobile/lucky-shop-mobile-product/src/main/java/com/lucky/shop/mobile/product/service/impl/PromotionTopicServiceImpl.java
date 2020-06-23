package com.lucky.shop.mobile.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.common.core.tool.Convert;
import com.lucky.shop.common.core.utils.StringUtil;
import com.lucky.shop.mobile.product.domain.PromotionTopic;
import com.lucky.shop.mobile.product.domain.ShopGoods;
import com.lucky.shop.mobile.product.mapper.PromotionTopicMapper;
import com.lucky.shop.mobile.product.service.PromotionTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 专题
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 2:24
 */
@Service
public class PromotionTopicServiceImpl extends ServiceImpl<PromotionTopicMapper, PromotionTopic> implements PromotionTopicService {

    @Autowired
    private ShopGoodsServiceImpl goodsService;

    /**
     * 专题列表
     *
     * @return
     */
    @Override
    public List<PromotionTopic> topicList() {
        Page<PromotionTopic> page = new Page<>();
        QueryWrapper<PromotionTopic> wrapper = new QueryWrapper<>();
        page.setSize(6);
        wrapper.orderByAsc(PromotionTopic.COL_ID);
        wrapper.eq(PromotionTopic.COL_DISABLED, false);
        List<PromotionTopic> topicList = this.page(page, wrapper).getRecords();
        return topicList;
    }

    /**
     * 查询专题
     *
     * @param id
     * @return
     */
    @Override
    public PromotionTopic get(Long id) {
        PromotionTopic topic = this.getById(id);
        topic.setPv(topic.getPv() + 1);
        this.updateById(topic);
        String idGoodsList = topic.getIdGoodsList();
        if (StringUtil.isNotEmpty(idGoodsList)) {
            Long[] idGoodsArr = Convert.toLongArray(",", idGoodsList);
            List<ShopGoods> goodsList = (List<ShopGoods>) goodsService.listByIds(Arrays.asList(idGoodsArr));
            topic.setGoodsList(goodsList);
        }
        return topic;
    }
}
