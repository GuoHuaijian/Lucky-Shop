package com.lucky.shop.admin.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.admin.mall.domain.ShopOrderLog;

import java.util.List;

/**
 * 订单日志
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 17:59
 */
public interface ShopOrderLogService extends IService<ShopOrderLog> {

    /**
     * 根据id获取订单日志
     *
     * @param idOrder
     * @return
     */
    List<ShopOrderLog> queryByIdOrder(Long idOrder);

    /**
     * 分页查询订单日志
     *
     * @return
     */
    Page<ShopOrderLog> ShopOrderLogList();

}
