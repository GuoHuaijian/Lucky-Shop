package com.lucky.shop.admin.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.shop.admin.system.domain.SysExpress;
import com.lucky.shop.admin.system.service.SysExpressService;
import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.common.core.utils.StringUtil;
import com.lucky.shop.common.log.annotation.BussinessLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物流公司
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 19:19
 */
@RestController
@RequestMapping("system/sys/express")
public class SysExpressController {

    @Autowired
    private SysExpressService expressService;

    /**
     * 分页查询物流公司
     *
     * @return
     */
    @GetMapping(value = "/list")
//    @RequiresPermissions(value = {Permission.EXPRESS})
    public ResponseResult list() {
        Page<SysExpress> sysExpressPage = expressService.expressList();
        return ResponseResult.success(sysExpressPage);
    }

    /**
     * 获取全部非禁用的物流公司列表
     *
     * @return
     */
    @GetMapping(value = "/queryAll")
//    @RequiresPermissions(value = {Permission.EXPRESS})
    public ResponseResult queryAll() {
        List<SysExpress> list = expressService.queryAll();
        return ResponseResult.success(list);
    }

    /**
     * 编辑物流公司
     *
     * @param express
     * @return
     */
    @PostMapping()
    @BussinessLog(value = "编辑物流公司", key = "name")
//    @RequiresPermissions(value = {Permission.EXPRESS_EDIT})
    public ResponseResult save(@ModelAttribute SysExpress express) {
        expressService.saveExpress(express);
        return ResponseResult.success();
    }

    /**
     * 删除物流公司
     *
     * @param id
     * @return
     */
    @DeleteMapping()
    @BussinessLog(value = "删除物流公司", key = "id")
//    @RequiresPermissions(value = {Permission.EXPRESS_EDIT})
    public ResponseResult remove(Long id) {
        if (StringUtil.isEmpty(id)) {
            throw new RuntimeException();
        }
        expressService.remove(id);
        return ResponseResult.success();
    }

    /**
     * 启用禁用物流公司
     *
     * @param id
     * @param disabled
     * @return
     */
    @PostMapping(value = "/changeDisabled")
//    @RequiresPermissions(value = {Permission.EXPRESS_EDIT})
    @BussinessLog(value = "启用禁用物流公司", key = "id")
    public ResponseResult changeIsOnSale(@RequestParam("id") Long id, @RequestParam("disabled") Boolean disabled) {
        if (id == null) {
            throw new RuntimeException();
        }
        expressService.changeIsOnSale(id, disabled);
        return ResponseResult.success();
    }

    /**
     * 通过id查询快递
     *
     * @param id
     * @return
     */
    @GetMapping("test/{id}")
    public SysExpress getExpressById(@PathVariable("id") Long id){
        SysExpress express = expressService.getById(id);
        return express;
    }
}
