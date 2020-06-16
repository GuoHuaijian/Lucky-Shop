package com.lucky.shop.admin.mall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.shop.admin.mall.domain.ShopGoods;
import com.lucky.shop.admin.mall.service.ShopGoodsService;
import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.common.log.annotation.BussinessLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author Guo Huaijian
 * @Date 2020/6/13 23:02
 */
@RestController
@RequestMapping("mall/shop/goods")
public class ShopGoodsController {

    @Autowired
    private ShopGoodsService goodsService;

    /**
     * 商品列表
     *
     * @param name
     * @return
     */
    @GetMapping(value = "/list")
    public ResponseResult list(@RequestParam(value = "name", required = false) String name) {
        Page<ShopGoods> page = goodsService.goodsList(name);
        return ResponseResult.success(page);
    }

    /**
     * 保存商品基本信息
     *
     * @param goods
     * @return
     */
    @PostMapping(value = "/saveBaseInfo")
    @BussinessLog(value = "保存商品基本信息", key = "name")
//    @RequiresPermissions(value = {Permission.GOODS_EDIT})
    public ResponseResult saveBaseInfo(@RequestBody ShopGoods goods) {
        if (goods.getId() == null) {
            goodsService.saveBaseInfo(goods);
        }
        return ResponseResult.success(goods.getId());
    }

    /**
     * 编辑商品
     *
     * @param goods
     * @return
     */
    @PostMapping()
    @BussinessLog(value = "编辑商品", key = "name")
//    @RequiresPermissions(value = {Permission.GOODS_EDIT})
    public ResponseResult save(@RequestBody @Valid ShopGoods goods) {
        goodsService.saveOrUpdateGoods(goods);
        return ResponseResult.success();
    }

    /**
     * 删除商品
     *
     * @param id
     * @return
     */
    @DeleteMapping()
    @BussinessLog(value = "删除商品", key = "id")
//    @RequiresPermissions(value = {Permission.GOODS_EDIT})
    public ResponseResult remove(Long id) {
        goodsService.remove(id);
        return ResponseResult.success();
    }

    /**
     * 获取商品详情
     *
     * @param id
     * @return
     */
    @GetMapping()
    public ResponseResult get(Long id) {
        return ResponseResult.success(goodsService.getDetail(id));
    }

    /**
     * 上架/下架商品
     *
     * @param id
     * @param isOnSale
     * @return
     */
    @PostMapping(value = "/changeIsOnSale")
//    @RequiresPermissions(value = {Permission.GOODS_EDIT})
    @BussinessLog(value = "上架/下架商品", key = "id")
    public ResponseResult changeIsOnSale(@RequestParam("id") Long id, @RequestParam("isOnSale") Boolean isOnSale) {
        ShopGoods goods = goodsService.changeIsOnSale(id, isOnSale);
        return ResponseResult.success(goods);
    }

}
