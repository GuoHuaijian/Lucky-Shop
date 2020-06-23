package com.lucky.shop.mobile.order.service;

/**
 * 支付
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 0:38
 */
public interface PayService {

    /**
     * 数据准备
     *
     * @param orderSn
     * @return
     */
    Object wxPrepare(String orderSn);

    /**
     * 微信支付回调
     *
     * @return
     */
    String wxNotify();

    /**
     * 查询支付结果
     *
     * @param orderSn
     * @return
     */
    Boolean wxNotify(String orderSn);
}
