package com.lucky.shop.mobile.order.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.mobile.order.domain.ShopOrder;
import com.lucky.shop.mobile.order.service.ShopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 订单
 *
 * @Author Guo Huaijian
 * @Date 2020/6/23 22:28
 */
@RestController
@RequestMapping("/user/order")
public class ShopOrderController {

    @Autowired
    private ShopOrderService orderService;

    /**
     * 订单编号查询订单
     *
     * @param orderSn
     * @return
     */
    @GetMapping(value = "{orderSn}")
    public ResponseResult get(@PathVariable(value = "orderSn") String orderSn) {
        ShopOrder order = orderService.get(orderSn);
        return ResponseResult.success(order);
    }

    /**
     * 根据状态查询订单
     *
     * @param status
     * @return
     */
    @GetMapping(value = "getOrders")
    public ResponseResult getOrders(@RequestParam(value = "status", required = false) Integer status) {
        Page<ShopOrder> orders = orderService.getOrders(status);
        return ResponseResult.success(orders);
    }

    /**
     * 地址查询订单
     *
     * @param chosenAddressId
     * @return
     */
    @GetMapping(value = "prepareCheckout")
    public ResponseResult prepareCheckout(@RequestParam(value = "chosenAddressId", required = false) Long chosenAddressId) {
        Map map = orderService.prepareCheckout(chosenAddressId);
        return ResponseResult.success(map);
    }

    /**
     * 保存订单
     *
     * @param idAddress
     * @param message
     * @return
     */
    @PostMapping(value = "save")
    public ResponseResult save(@RequestParam("idAddress") Long idAddress,
                               @RequestParam(value = "message", required = false) String message) {
        ShopOrder order = orderService.save(idAddress, message);
        return ResponseResult.success(order);
    }

    /**
     * 用户取消订单
     *
     * @param orderSn
     * @return
     */
    @PostMapping(value = "cancel/{orderSn}")
    public ResponseResult cancel(@PathVariable("orderSn") String orderSn) {
        orderService.cancel(orderSn);
        return ResponseResult.success();
    }

    /**
     * 确认收货
     *
     * @param orderSn
     * @return
     */
    @RequestMapping(value = "confirm/{orderSn}", method = RequestMethod.POST)
    public ResponseResult confirm(@PathVariable("orderSn") String orderSn) {
        ShopOrder order = orderService.confirm(orderSn);
        return ResponseResult.success(order);
    }
}
