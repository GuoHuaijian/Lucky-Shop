package com.lucky.shop.admin.mall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.shop.admin.mall.domain.ShopGoodsSku;
import com.lucky.shop.admin.mall.service.ShopGoodsSkuService;
import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.common.log.annotation.BussinessLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 商品SKU
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 0:05
 */
@RestController
@RequestMapping("/shop/goods/sku")
public class ShopGoodsSkuController {

    @Autowired
    private ShopGoodsSkuService goodsSkuService;

    /**
     * 获取商品SKU
     *
     * @return
     */
    @GetMapping(value = "/list")
    public ResponseResult list() {
        Page<ShopGoodsSku> page = goodsSkuService.goodsSkuList();
        return ResponseResult.success(page);
    }

    /**
     * 编辑商品SKU
     *
     * @param sku
     * @return
     */
    @PostMapping()
    @BussinessLog(value = "编辑商品SKU", key = "name")
//    @RequiresPermissions(value = {Permission.GOODS_EDIT})
    public ResponseResult save(@RequestBody ShopGoodsSku sku) {
        ShopGoodsSku goodsSku = goodsSkuService.saveSku(sku);
        return ResponseResult.success(goodsSku);
    }

    /**
     * 删除商品SKU
     *
     * @param id
     * @return
     */
    @DeleteMapping()
    @BussinessLog(value = "删除商品SKU", key = "id")
//    @RequiresPermissions(value = {Permission.GOODS_EDIT})
    public ResponseResult remove(Long id) {
        goodsSkuService.remove(id);
        return ResponseResult.success();
    }
}
