package com.lucky.shop.mobile.order.service.impl;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.lucky.shop.common.core.enums.OrderEnum;
import com.lucky.shop.common.core.utils.HttpUtil;
import com.lucky.shop.mobile.order.domain.ShopOrder;
import com.lucky.shop.mobile.order.service.ShopOrderService;
import com.lucky.shop.mobile.ucenter.api.domain.ShopUser;
import org.apache.commons.io.IOUtils;
import org.nutz.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * 微信支付
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 1:02
 */
@Service
public class WeixinPayService {

    @Resource
    private WxPayService wxPayService;

    @Autowired
    private ShopOrderService orderService;

    private Logger logger = LoggerFactory.getLogger(WeixinPayService.class);

    public WxPayMpOrderResult prepare(ShopUser user, ShopOrder order) {
        WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
        orderRequest.setOutTradeNo(order.getOrderSn());
        orderRequest.setOpenid(user.getWechatOpenId());
        orderRequest.setBody("订单：" + order.getOrderSn());
        BigDecimal totalPrice = order.getTotalPrice();
        orderRequest.setTotalFee(totalPrice.intValue());
        orderRequest.setSpbillCreateIp(HttpUtil.getIp());
        try {
            WxPayMpOrderResult result = wxPayService.createOrder(orderRequest);
            orderService.startPay(order);
            return result;
        } catch (WxPayException e) {
            logger.error("微信支付异常", e);
        }
        return null;
    }

    public String resultNotify() {
        String xmlResult = null;
        try {
            HttpServletRequest request = HttpUtil.getRequest();
            xmlResult = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
        } catch (IOException e) {
            logger.error("解析微信支付结果通知异常", e);
            return WxPayNotifyResponse.fail(e.getMessage());
        }
        WxPayOrderNotifyResult result = null;
        try {
            result = wxPayService.parseOrderNotifyResult(xmlResult);

            if (!WxPayConstants.ResultCode.SUCCESS.equals(result.getResultCode())) {
                logger.error(xmlResult);
                throw new WxPayException("微信通知支付失败！");
            }
            if (!WxPayConstants.ResultCode.SUCCESS.equals(result.getReturnCode())) {
                logger.error(xmlResult);
                throw new WxPayException("微信通知支付失败！");
            }
        } catch (WxPayException e) {
            e.printStackTrace();
            return WxPayNotifyResponse.fail(e.getMessage());
        }

        logger.info("处理腾讯支付平台的订单支付", Json.toJson(result));

        String orderSn = result.getOutTradeNo();
        String payId = result.getTransactionId();

        Integer totalFee = result.getTotalFee();
        ShopOrderServiceImpl shopOrderService = new ShopOrderServiceImpl();
        ShopOrder order = shopOrderService.getByOrderSn(orderSn);
        if (order == null) {
            return WxPayNotifyResponse.fail("订单不存在 sn=" + orderSn);
        }

        // 检查这个订单是否已经处理过
        if (order.hasPayed()) {
            return WxPayNotifyResponse.success("订单已经处理成功!");
        }

        // 检查支付订单金额
        if (totalFee.intValue() != order.getTotalPrice().intValue()) {
            return WxPayNotifyResponse.fail(order.getOrderSn() + " : 支付金额不符合 totalFee=" + totalFee);
        }

        order.setPayId(payId);
        orderService.paySuccess(order, OrderEnum.PayTypeEnum.UN_SEND.getKey());
        //todo 发送短信通知
        //todo 发送微信模板消息
        return WxPayNotifyResponse.success("支付成功！");
    }
}
