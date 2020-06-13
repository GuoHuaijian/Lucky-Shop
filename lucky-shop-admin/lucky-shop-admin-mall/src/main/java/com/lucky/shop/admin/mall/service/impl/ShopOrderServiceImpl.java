package com.lucky.shop.admin.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.mall.domain.ShopOrder;
import com.lucky.shop.admin.mall.domain.ShopOrderLog;
import com.lucky.shop.admin.mall.mapper.ShopOrderMapper;
import com.lucky.shop.admin.mall.service.ShopOrderLogService;
import com.lucky.shop.admin.mall.service.ShopOrderService;
import com.lucky.shop.admin.mall.utils.SysUserUtils;
import com.lucky.shop.common.core.enums.OrderEnum;
import com.lucky.shop.common.core.tool.Maps;
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

    @Autowired
    private ShopOrderLogService orderLogService;

    @Autowired
    private SysUserUtils sysUserUtils;

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
     * 保存订单日志
     *
     * @param order
     * @param descript
     */
    private void saveOrderLog(ShopOrder order, String descript) {
        ShopOrderLog orderLog = new ShopOrderLog();
        orderLog.setIdOrder(order.getId());
        orderLog.setDescript(descript);
        orderLogService.save(orderLog);
    }

    /**
     * 更新发货信息
     *
     * @param order
     */
    @Override
    public void send(ShopOrder order) {
        String descript = "管理员(" + sysUserUtils.getUsername() + ")已发货";
        updateById(order);
        saveOrderLog(order, descript);
    }

    /**
     * 管理员添加备注信息
     *
     * @param order
     * @param message
     */
    @Override
    public void addComment(ShopOrder order, String message) {
        order.setAdminMessage(message);
        updateById(order);
        ShopOrderLog orderLog = new ShopOrderLog();
        orderLog.setIdOrder(order.getId());
        orderLog.setDescript("管理员(" + sysUserUtils.getUsername() + ")添加备注：" + message);
        orderLogService.save(orderLog);
    }

    /**
     * 根据订单编号获取订单
     *
     * @param orderSn
     * @return
     */
    @Override
    public ShopOrder getByOrderSn(String orderSn) {
        QueryWrapper<ShopOrder> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopOrder.COL_ORDER_SN, orderSn);
        ShopOrder shopOrder = orderMapper.selectOne(wrapper);
        return shopOrder;
    }

}
