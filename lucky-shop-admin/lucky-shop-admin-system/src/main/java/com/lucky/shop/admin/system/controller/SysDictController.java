package com.lucky.shop.admin.system.controller;

import com.lucky.shop.admin.system.domain.SysDict;
import com.lucky.shop.admin.system.service.SysDictService;
import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.common.core.utils.BeanUtil;
import com.lucky.shop.common.core.warpper.DictWarpper;
import com.lucky.shop.common.log.annotation.BussinessLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 18:28
 */
@RestController
@RequestMapping("system/dict")
public class SysDictController {

    @Autowired
    private SysDictService dictService;

    /**
     * 获取所有字典列表
     *
     * @param name
     * @return
     */
    @GetMapping(value = "/list")
//    @RequiresPermissions(value = {Permission.DICT})
    public ResponseResult list(String name) {
        List<SysDict> list = dictService.list(name);
        // TODO 待完善
        return ResponseResult.success(new DictWarpper(BeanUtil.objectsToMaps(list)).warp());
    }

    /**
     * 添加字典
     *
     * @param dictName
     * @param dictValues
     * @return
     */
    @PostMapping()
    @BussinessLog(value = "添加字典", key = "dictName")
//    @RequiresPermissions(value = {Permission.DICT_EDIT})
    public ResponseResult add(String dictName, String dictValues) {
        if (BeanUtil.isOneEmpty(dictName, dictValues)) {
            throw new RuntimeException();
        }
        dictService.add(dictName, dictValues);
        return ResponseResult.success();
    }

    /**
     * 修改字典
     *
     * @param id
     * @param dictName
     * @param dictValues
     * @return
     */
    @PutMapping()
    @BussinessLog(value = "修改字典", key = "dictName")
//    @RequiresPermissions(value = {Permission.DICT_EDIT})
    public ResponseResult update(Long id, String dictName, String dictValues) {
        if (BeanUtil.isOneEmpty(dictName, dictValues)) {
            throw new RuntimeException();
        }
        dictService.update(id, dictName, dictValues);
        return ResponseResult.success();
    }

    /**
     * 删除字典
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @BussinessLog(value = "删除字典", key = "id")
//    @RequiresPermissions(value = {Permission.DICT_DEL})
    public ResponseResult delete(@RequestParam Long id) {
        dictService.delete(id);
        return ResponseResult.success();
    }
}
