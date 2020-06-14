package com.lucky.shop.admin.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.admin.system.domain.SysExpress;

import java.util.List;

/**
 * 物流公司
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 19:02
 */
public interface SysExpressService extends IService<SysExpress> {

    /**
     * 分页查询物流公司
     *
     * @return
     */
    Page<SysExpress> expressList();

    /**
     * 获取全部非禁用的物流公司列表
     *
     * @return
     */
    List<SysExpress> queryAll();

    /**
     * 编辑物流公司
     *
     * @param express
     * @return
     */
    void saveExpress(SysExpress express);

    /**
     * 删除物流公司
     *
     * @param id
     * @return
     */
    void remove(Long id);

    /**
     * 启用禁用物流公司
     *
     * @param id
     * @param disabled
     * @return
     */
    void changeIsOnSale(Long id, Boolean disabled);


}
