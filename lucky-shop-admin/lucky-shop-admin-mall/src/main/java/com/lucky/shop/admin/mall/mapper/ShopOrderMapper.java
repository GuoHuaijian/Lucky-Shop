package com.lucky.shop.admin.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lucky.shop.admin.mall.domain.ShopOrder;
import org.apache.ibatis.annotations.Select;

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

    /**
     * 获取订单总金额
     *
     * @return
     */
    @Select("select sum(real_price) as realPrice from t_shop_order where status<>1")
    Map getRealPrice();
}
