package com.lucky.shop.admin.mall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.shop.admin.mall.domain.ShopOrderLog;
import com.lucky.shop.admin.mall.service.ShopOrderLogService;
import com.lucky.shop.common.core.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 订单日志
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 19:20
 */
@RestController
@RequestMapping("mall/shop/order/log")
public class ShopOrderLogController {

    @Autowired
    private ShopOrderLogService orderLogService;

    /**
     * 根据id获取订单日志
     *
     * @param idOrder
     * @return
     */
    @GetMapping(value = "/queryByIdOrder/{idOrder}")
    public ResponseResult queryByIdOrder(@PathVariable("idOrder") Long idOrder) {
        List<ShopOrderLog> logList = orderLogService.queryByIdOrder(idOrder);
        return ResponseResult.success(logList);
    }

    /**
     * 分页查询订单日志
     *
     * @return
     */
    @GetMapping(value = "/list")
    public ResponseResult list() {
        Page<ShopOrderLog> shopOrderLogPage = orderLogService.ShopOrderLogList();
        return ResponseResult.success(shopOrderLogPage);
    }
}
