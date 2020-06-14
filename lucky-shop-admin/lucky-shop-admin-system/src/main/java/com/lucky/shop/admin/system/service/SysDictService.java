package com.lucky.shop.admin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.admin.system.domain.SysDict;

import java.util.List;

/**
 * 字典
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 18:26
 */
public interface SysDictService extends IService<SysDict> {

    /**
     * 获取所有字典列表
     *
     * @param name
     * @return
     */
    List<SysDict> list(String name);

    /**
     * 添加字典
     *
     * @param dictName
     * @param dictValues
     */
    void add(String dictName, String dictValues);

    /**
     * 修改字典
     *
     * @param id
     * @param dictName
     * @param dictValues
     */
    void update(Long id, String dictName, String dictValues);

    /**
     * 删除字典
     *
     * @param id
     */
    void delete(Long id);

}
