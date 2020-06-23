package com.lucky.shop.mobile.order.controller;

import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.mobile.order.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 支付
 *
 * @Author Guo Huaijian
 * @Date 2020/6/23 22:30
 */
@RestController
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private PayService payService;

    /**
     * 数据准备
     *
     * @param orderSn
     * @return
     */
    @PostMapping(value = "wx/prepare")
    public ResponseResult wxPrepare(@RequestParam("orderSn") String orderSn) {
        Object prepare = payService.wxPrepare(orderSn);
        return ResponseResult.success(prepare);
    }

    /**
     * 微信支付回调
     *
     * @return
     */
    @PostMapping(value = "wx/notify")
    public ResponseResult wxNotify() {
        String msg = payService.wxNotify();
        return ResponseResult.success(msg);
    }

    /**
     * 查询支付结果
     *
     * @param orderSn
     * @return
     */
    @GetMapping(value = "queryResult/{orderSn}")
    public ResponseResult wxNotify(@PathVariable("orderSn") String orderSn) {
        Boolean payResult = payService.wxNotify(orderSn);
        return ResponseResult.success(payResult);
    }
}
