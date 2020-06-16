package com.lucky.shop.admin.mall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.shop.admin.mall.domain.ShopAttrKey;
import com.lucky.shop.admin.mall.service.ShopAttrKeyService;
import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.common.log.annotation.BussinessLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 商品属性名
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 20:20
 */
@RestController
@RequestMapping("mall/shop/goods/attr/key")
public class ShopAttrKeyController {

    @Autowired
    private ShopAttrKeyService attrKeyService;

    /**
     * 获取属性列表
     *
     * @return
     */
    @GetMapping(value = "/list")
    public ResponseResult list() {
        Page<ShopAttrKey> page = attrKeyService.attrKeyList();
        return ResponseResult.success(page);
    }

    /**
     * 编辑商品属性名
     *
     * @param attrKey
     * @return
     */
    @PostMapping()
    @BussinessLog(value = "编辑商品属性名", key = "name")
    public ResponseResult save(@ModelAttribute ShopAttrKey attrKey) {
        attrKeyService.SaveOrUpdateAttrKey(attrKey);
        return ResponseResult.success();
    }

    /**
     * 删除商品属性名
     *
     * @param id
     * @return
     */
    @DeleteMapping()
    @BussinessLog(value = "删除商品属性名", key = "id")
    public ResponseResult remove(Long id) {
        attrKeyService.remove(id);
        return ResponseResult.success();
    }

    /**
     * 修改商品属性名
     *
     * @param id
     * @param attrName
     * @return
     */
    @PostMapping(value = "updateAttrName")
    @BussinessLog(value = "修改商品属性名", key = "id")
    public ResponseResult updateAttrName(@RequestParam("id") Long id, @RequestParam("attrName") String attrName) {
        attrKeyService.updateAttrName(id, attrName);
        return ResponseResult.success();
    }
}
