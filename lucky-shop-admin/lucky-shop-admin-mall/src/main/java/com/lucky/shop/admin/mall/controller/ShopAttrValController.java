package com.lucky.shop.admin.mall.controller;

import com.lucky.shop.admin.mall.domain.ShopAttrVal;
import com.lucky.shop.admin.mall.service.ShopAttrValService;
import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.common.log.annotation.BussinessLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 商品属性值
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 20:28
 */
@RestController
@RequestMapping("/shop/attr/val")
public class ShopAttrValController {

    @Autowired
    private ShopAttrValService attrValService;

    /**
     * 获取属性键值对
     *
     * @param idCategory
     * @return
     */
    @GetMapping(value = "/getAttrByCategoryAndGoods/{idCategory}")
    public ResponseResult getAttrByCategoryAndGoods(@PathVariable("idCategory") Long idCategory) {
        Map attrByCategoryAndGoods = attrValService.getAttrByCategoryAndGoods(idCategory);
        return ResponseResult.success(attrByCategoryAndGoods);
    }

    /**
     * 获取商品属性值列表
     *
     * @param idAttrKey
     * @return
     */
    @GetMapping(value = "getAttrVals")
    public ResponseResult getAttrVals(@RequestParam("idAttrKey") Long idAttrKey) {
        List<ShopAttrVal> list = attrValService.getAttrVals(idAttrKey);
        return ResponseResult.success(list);
    }

    /**
     * 编辑商品属性值
     *
     * @param idAttrKey
     * @param id
     * @param attrVal
     * @return
     */
    @PostMapping()
    @BussinessLog(value = "编辑商品属性值", key = "attrVal")
    public ResponseResult save(@RequestParam("idAttrKey") Long idAttrKey,
                               @RequestParam(value = "id", required = false) Long id,
                               @RequestParam("attrVal") String attrVal) {
        String message = (String) attrValService.save(idAttrKey, id, attrVal);
        return ResponseResult.success(message);
    }

    /**
     * 删除商品属性值
     *
     * @param id
     * @return
     */
    @DeleteMapping()
    @BussinessLog(value = "删除商品属性值", key = "id")
    public ResponseResult remove(Long id) {
        attrValService.remove(id);
        return ResponseResult.success();
    }

    /**
     * 修改商品属性值
     *
     * @param id
     * @param attrVal
     * @return
     */
    @PostMapping(value = "updateAttrVal")
    @BussinessLog(value = "修改商品属性值", key = "id")
    public ResponseResult updateAttrName(@RequestParam("id") Long id, @RequestParam("attrVal") String attrVal) {
        attrValService.updateAttrName(id, attrVal);
        return ResponseResult.success();
    }
}
