package com.lucky.shop.admin.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lucky.shop.admin.mall.domain.ShopOrder;

import java.util.List;
import java.util.Map;

/**
 * @Author Guo Huaijian
 * @Date 2020/6/5 12:55
 */
public interface ShopOrderMapper extends BaseMapper<ShopOrder> {

    /**
     * 获取订单统计信息
     *
     * @return
     */
    List<Map> getOrderStatistic();
}
