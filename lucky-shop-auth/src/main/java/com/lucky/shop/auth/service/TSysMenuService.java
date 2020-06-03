package com.lucky.shop.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.auth.domain.TSysMenu;

import java.util.List;

/**
 * 菜单Service
 *
 * @Author Guo Huaijian
 * @Date 2020/5/17 18:49
 */
public interface TSysMenuService extends IService<TSysMenu> {


    /**
     * 查询菜单地址
     *
     * @param roleId
     * @return
     */
    List<String> getResUrlsByRoleId(Long roleId);

    /**
     * 查询菜单编号
     *
     * @param roleId
     * @return
     */
    List<String> getResCodesByRoleId(Long roleId);

}
