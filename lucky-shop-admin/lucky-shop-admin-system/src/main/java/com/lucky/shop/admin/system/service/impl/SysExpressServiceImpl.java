package com.lucky.shop.admin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.system.domain.SysExpress;
import com.lucky.shop.admin.system.mapper.SysExpressMapper;
import com.lucky.shop.admin.system.service.SysExpressService;
import com.lucky.shop.common.core.factory.PageFactory;
import com.lucky.shop.common.core.tool.LogObjectHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物流公司
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 19:02
 */
@Service
public class SysExpressServiceImpl extends ServiceImpl<SysExpressMapper, SysExpress> implements SysExpressService {

    /**
     * 分页查询物流公司
     *
     * @return
     */
    @Override
    public Page<SysExpress> expressList() {
        Page<SysExpress> page = new PageFactory<SysExpress>().defaultPage();
        QueryWrapper<SysExpress> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc(SysExpress.COL_SORT);
        IPage<SysExpress> ExpressPage = this.page(page, wrapper);
        return (Page<SysExpress>) ExpressPage;
    }

    /**
     * 获取全部非禁用的物流公司列表
     *
     * @return
     */
    @Override
    public List<SysExpress> queryAll() {
        QueryWrapper<SysExpress> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc(SysExpress.COL_SORT);
        wrapper.eq(SysExpress.COL_DISABLED, false);
        List<SysExpress> list = this.list(wrapper);
        return list;
    }

    /**
     * 编辑物流公司
     *
     * @param express
     */
    @Override
    public void saveExpress(SysExpress express) {
        if (express.getId() == null) {
            this.save(express);
        } else {
            SysExpress old = this.getById(express.getId());
            LogObjectHolder.me().set(old);
            this.updateById(express);
        }
    }

    /**
     * 删除物流公司
     *
     * @param id
     * @return
     */
    @Override
    public void remove(Long id) {
        this.removeById(id);
    }

    /**
     * 启用禁用物流公司
     *
     * @param id
     * @param disabled
     * @return
     */
    @Override
    public void changeIsOnSale(Long id, Boolean disabled) {
        this.changeDisabled(id, disabled);
    }


    public void changeDisabled(Long id, Boolean disabled) {
        SysExpress express = getById(id);
        express.setDisabled(disabled);
        updateById(express);
    }
}
