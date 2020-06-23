package com.lucky.shop.mobile.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.mobile.order.domain.ShopOrder;

import java.util.Map;

/**
 * 订单
 *
 * @Author Guo Huaijian
 * @Date 2020/6/23 22:28
 */
public interface ShopOrderService extends IService<ShopOrder> {

    /**
     * 订单编号查询订单
     *
     * @param orderSn
     * @return
     */
    ShopOrder get(String orderSn);

    /**
     * 根据状态查询订单
     *
     * @param status
     * @return
     */
    Page<ShopOrder> getOrders(Integer status);

    /**
     * 地址查询订单
     *
     * @param chosenAddressId
     * @return
     */
    Map prepareCheckout(Long chosenAddressId);

    /**
     * 保存订单
     *
     * @param idAddress
     * @param message
     * @return
     */
    ShopOrder save(Long idAddress, String message);

    /**
     * 用户取消订单
     *
     * @param orderSn
     */
    void cancel(String orderSn);

    /**
     * 确认收货
     *
     * @param orderSn
     * @return
     */
    ShopOrder confirm(String orderSn);

    /**
     * 发起支付
     *
     * @param order
     */
    void startPay(ShopOrder order);

    /**
     * 支付成功，更新订单数据
     *
     * @param order
     * @param payType
     */
    void paySuccess(ShopOrder order, String payType);
}
