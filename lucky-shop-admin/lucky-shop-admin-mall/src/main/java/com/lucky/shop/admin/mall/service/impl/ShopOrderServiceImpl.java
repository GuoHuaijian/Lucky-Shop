package com.lucky.shop.admin.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.mall.domain.ShopOrder;
import com.lucky.shop.admin.mall.mapper.ShopOrderMapper;
import com.lucky.shop.admin.mall.service.ShopOrderService;
import com.lucky.shop.common.enums.OrderEnum;
import com.lucky.shop.common.tool.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author Guo Huaijian
 * @Date 2020/6/5 12:55
 */
@Service
public class ShopOrderServiceImpl extends ServiceImpl<ShopOrderMapper, ShopOrder> implements ShopOrderService {

    @Autowired
    private ShopOrderMapper orderMapper;

    /**
     * 获取订单统计信息
     *
     * @return
     */
    @Override
    public Map getOrderStatistic() {
        List<Map> list = orderMapper.getOrderStatistic();
        Map result = Maps.newHashMap();
        for (Map map : list) {
            String statusStr = OrderEnum.getStatusStr((Integer) map.get("status"));
            result.put(statusStr, map.get("count"));
        }
        return result;
    }

    /**
     * 更新发货信息
     * @param order
     */
    @Override
    public void send(ShopOrder order) {
//        String descript = "管理员("+JwtUtil.getUsername()+")已发货";
//        updateById(order);
//        saveOrderLog(order,descript);
    }

}
