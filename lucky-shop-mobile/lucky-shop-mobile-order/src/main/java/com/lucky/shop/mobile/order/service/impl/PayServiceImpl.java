package com.lucky.shop.mobile.order.service.impl;

import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.lucky.shop.common.core.enums.OrderEnum;
import com.lucky.shop.common.core.utils.StringUtil;
import com.lucky.shop.mobile.order.domain.ShopOrder;
import com.lucky.shop.mobile.order.service.PayService;
import com.lucky.shop.mobile.ucenter.api.RemoteShopUserService;
import com.lucky.shop.mobile.ucenter.api.domain.ShopUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * 支付
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 0:38
 */
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private RemoteShopUserService shopUserService;

    @Autowired
    private WeixinPayService weixinPayService;

    /**
     * 数据准备
     *
     * @param orderSn
     * @return
     */
    @Override
    public Object wxPrepare(String orderSn) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String account = authentication.getName();
        ShopUser user = shopUserService.getUserByAccount(account);
        if (StringUtil.isEmpty(user.getWechatOpenId())) {
            return "非微信用户";
        }
        ShopOrderServiceImpl shopOrderService = new ShopOrderServiceImpl();
        ShopOrder order = shopOrderService.getByOrderSn(orderSn);
        WxPayMpOrderResult wxOrder = weixinPayService.prepare(user, order);
//        Map map = Maps.newHashMap(
//                "appId","aa",
//                "nonceStr","aaa",
//                "package","aaa",
//                "paySign","aaa",
//                "signType","aaa",
//                "timeStamp","aa"
//        );
        if (wxOrder != null) {
            return wxOrder;
        }
        return "数据准备异常";
    }

    /**
     * 微信支付回调
     *
     * @return
     */
    @Override
    public String wxNotify() {
        String msg = weixinPayService.resultNotify();
        return msg;
    }

    /**
     * 查询支付结果
     *
     * @param orderSn
     * @return
     */
    @Override
    public Boolean wxNotify(String orderSn) {
        ShopOrderServiceImpl shopOrderService = new ShopOrderServiceImpl();
        ShopOrder order = shopOrderService.getByOrderSn(orderSn);
        Boolean payResult = OrderEnum.PayStatusEnum.UN_SEND.getId().equals(order.getPayStatus())
                && OrderEnum.PayStatusEnum.UN_SEND.getId().equals(order.getStatus());
        return payResult;
    }
}
