package com.lucky.shop.admin.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.mall.domain.ShopAttrKey;
import com.lucky.shop.admin.mall.domain.ShopAttrVal;
import com.lucky.shop.admin.mall.mapper.ShopAttrValMapper;
import com.lucky.shop.admin.mall.service.ShopAttrKeyService;
import com.lucky.shop.admin.mall.service.ShopAttrValService;
import com.lucky.shop.common.core.tool.Maps;
import com.lucky.shop.common.core.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 商品属性值
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 20:28
 */
@Service
public class ShopAttrValServiceImpl extends ServiceImpl<ShopAttrValMapper, ShopAttrVal> implements ShopAttrValService {

    @Autowired
    private ShopAttrKeyService attrKeyService;

    /**
     * 获取属性键值对
     *
     * @param idCategory
     * @return
     */
    @Override
    public Map getAttrByCategoryAndGoods(Long idCategory) {
        QueryWrapper<ShopAttrKey> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopAttrKey.COL_ID_CATEGORY, idCategory);
        List<ShopAttrKey> keyList = attrKeyService.list(wrapper);
        List<Long> idAttrKeyList = Lists.newArrayList();
        for (ShopAttrKey attrKey : keyList) {
            idAttrKeyList.add(attrKey.getId());
        }
        List<ShopAttrVal> valList = Lists.newArrayList();
        if (!idAttrKeyList.isEmpty()) {
            QueryWrapper<ShopAttrVal> queryWrapper = new QueryWrapper<>();
            queryWrapper.in(ShopAttrVal.COL_ID_ATTR_KEY, idAttrKeyList);
            valList = this.list(queryWrapper);
        }
        return Maps.newHashMap("keyList", keyList, "valList", valList);
    }

    /**
     * 获取商品属性值列表
     *
     * @param idAttrKey
     * @return
     */
    @Override
    public List<ShopAttrVal> getAttrVals(Long idAttrKey) {
        QueryWrapper<ShopAttrVal> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopAttrVal.COL_ID_ATTR_KEY, idAttrKey);
        List<ShopAttrVal> list = this.list(wrapper);
        return list;
    }

    /**
     * 编辑商品属性值
     *
     * @param idAttrKey
     * @param id
     * @param attrVal
     */
    @Override
    public Object save(Long idAttrKey, Long id, String attrVal) {
        ShopAttrVal entity;
        if (id != null) {
            entity = this.getById(id);
        }
        QueryWrapper<ShopAttrVal> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopAttrVal.COL_ID_ATTR_KEY, idAttrKey);
        wrapper.eq(ShopAttrVal.COL_ATTR_VAL, attrVal);
        entity = this.getOne(wrapper);
        if (id == null) {
            if (entity == null) {
                entity.setIdAttrKey(idAttrKey);
                entity.setAttrVal(attrVal);
                this.save(entity);
            } else {
                return "不能添加重复的规格";
            }
        } else {
            if (entity != null) {
                return "不能添加重复的规格";
            } else {
                entity.setIdAttrKey(idAttrKey);
                entity.setAttrVal(attrVal);
                this.updateById(entity);
            }
        }
        return null;
    }

    /**
     * 删除商品属性值
     *
     * @param id
     */
    @Override
    public void remove(Long id) {
        this.removeById(id);
    }

    /**
     * 修改商品属性值
     *
     * @param id
     * @param attrVal
     */
    @Override
    public void updateAttrName(Long id, String attrVal) {
        ShopAttrVal attrValEntity = this.getById(id);
        attrValEntity.setAttrVal(attrVal);
        this.updateById(attrValEntity);
    }
}
