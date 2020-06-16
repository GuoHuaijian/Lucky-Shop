package com.lucky.shop.admin.mall.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.shop.admin.mall.domain.ShopOrderItem;
import com.lucky.shop.admin.mall.service.ShopOrderItemService;
import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.common.core.factory.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单明细
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 0:14
 */
@RestController
@RequestMapping("mall/shop/order/item")
public class ShopOrderItemController {

    @Autowired
    private ShopOrderItemService orderItemService;

    /**
     * 订单明细列表
     *
     * @return
     */
    @GetMapping(value = "/list")
    public ResponseResult list() {
        Page<ShopOrderItem> page = new PageFactory<ShopOrderItem>().defaultPage();
        IPage<ShopOrderItem> ShopOrderItemPage = orderItemService.page(page);
        return ResponseResult.success(ShopOrderItemPage);
    }
}
