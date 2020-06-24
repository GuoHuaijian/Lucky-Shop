package com.lucky.shop.mobile.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.mobile.product.domain.ShopGoods;
import com.lucky.shop.mobile.product.service.ShopGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 商品
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 1:46
 */
@RestController
@RequestMapping("mobile/product/goods")
public class ShopGoodsController {

    @Autowired
    private ShopGoodsService goodsService;

    /**
     * 获取指定类别下的商品列表
     *
     * @param idCategory
     * @return
     */
    @GetMapping(value = "/queryGoods")
    public ResponseResult queryGoods(@RequestParam("idCategory") Long idCategory) {
        Page<ShopGoods> shopGoodsPage = goodsService.queryGoods(idCategory);
        return ResponseResult.success(shopGoodsPage);
    }

    /**
     * 根据关键字搜索商品
     *
     * @param key
     * @return
     */
    @GetMapping(value = "/search")
    public ResponseResult search(@RequestParam("key") String key) {
        Page<ShopGoods> goodsPage = goodsService.search(key);
        return ResponseResult.success(goodsPage);
    }

    /**
     * 查询新上架
     *
     * @return
     */
    @GetMapping(value = "/searchNew")
    public ResponseResult searchNew() {
        List<ShopGoods> goodsList = goodsService.searchNew();
        return ResponseResult.success(goodsList);
    }

    /**
     * 查询热门商品
     *
     * @return
     */
    @GetMapping(value = "/searchHot")
    public ResponseResult searchHot() {
        List<ShopGoods> goodsList = goodsService.searchHot();
        return ResponseResult.success(goodsList);
    }

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseResult get(@PathVariable Long id) {
        Map map = goodsService.get(id);
        return ResponseResult.success(map);
    }

    /**
     * 根据主键获取商品
     *
     * @param id
     * @return
     */
    @GetMapping("getOne/{id}")
    public ShopGoods getOne(@PathVariable Long id) {
        return goodsService.getById(id);
    }

    /**
     * 保存商品
     *
     * @param goods
     */
    @PostMapping()
    public void save(@RequestBody ShopGoods goods) {
        goodsService.save(goods);
    }
}
