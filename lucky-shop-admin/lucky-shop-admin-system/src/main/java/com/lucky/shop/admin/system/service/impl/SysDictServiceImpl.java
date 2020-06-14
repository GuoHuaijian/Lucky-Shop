package com.lucky.shop.admin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.system.domain.SysDict;
import com.lucky.shop.admin.system.mapper.SysDictMapper;
import com.lucky.shop.admin.system.service.SysDictService;
import com.lucky.shop.common.core.factory.MutiStrFactory;
import com.lucky.shop.common.core.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 字典
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 18:26
 */
@Slf4j
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

    /**
     * 获取所有字典列表
     *
     * @param name
     * @return
     */
    @Override
    public List<SysDict> list(String name) {
        if (StringUtil.isNotEmpty(name)) {
            List<SysDict> list = this.findByNameLike(name);
            return list;
        }
        List<SysDict> list = this.findByPid(0L);
        return list;
    }

    /**
     * 添加字典
     *
     * @param dictName
     * @param dictValues
     */
    @Override
    public void add(String dictName, String dictValues) {
        this.addDict(dictName, dictValues);
    }

    /**
     * 修改字典
     *
     * @param id
     * @param dictName
     * @param dictValues
     */
    @Override
    public void update(Long id, String dictName, String dictValues) {
        this.editDict(id, dictName, dictValues);
    }

    /**
     * 删除字典
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        this.delteDict(id);
    }


    public List<SysDict> findByNameLike(String name) {
        QueryWrapper<SysDict> wrapper = new QueryWrapper<>();
        wrapper.like(SysDict.COL_NAME, name);
        return this.list(wrapper);
    }

    public List<SysDict> findByPid(Long pid) {
        QueryWrapper<SysDict> wrapper = new QueryWrapper<>();
        wrapper.eq(SysDict.COL_PID, pid);
        return this.list(wrapper);
    }

    public void addDict(String dictName, String dictValues) {
        // 判断有没有该字典
        QueryWrapper<SysDict> wrapper = new QueryWrapper<>();
        wrapper.eq(SysDict.COL_PID, 0L);
        wrapper.eq(SysDict.COL_NAME, dictName);
        List<SysDict> dicts = this.list(wrapper);
        if (dicts != null && dicts.size() > 0) {
            return;
        }

        // 解析dictValues
        List<Map<String, String>> items = MutiStrFactory.parseKeyValue(dictValues);

        // 添加字典
        SysDict dict = new SysDict();
        dict.setName(dictName);
        dict.setNum("0");
        dict.setPid(0L);
        this.save(dict);

        // 添加字典条目
        for (Map<String, String> item : items) {
            String num = item.get(MutiStrFactory.MUTI_STR_KEY);
            String name = item.get(MutiStrFactory.MUTI_STR_VALUE);
            SysDict itemDict = new SysDict();
            itemDict.setPid(dict.getId());
            itemDict.setName(name);
            try {
                itemDict.setNum(num);
            } catch (NumberFormatException e) {
                log.error(e.getMessage(), e);
            }
            this.save(itemDict);
        }
    }

    public void editDict(Long dictId, String dictName, String dicts) {
        // 删除之前的字典
        this.delteDict(dictId);
        // 重新添加新的字典
        this.addDict(dictName, dicts);

    }

    public void delteDict(Long dictId) {
        // 删除这个字典的子词典
        List<SysDict> subList = this.findByPid(dictId);
        this.removeByIds(subList);
        // 删除这个词典
        this.removeById(dictId);
    }
}
