package com.lucky.shop.admin.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.admin.auth.domain.SysMenu;

import java.util.List;

/**
 * 菜单Service
 *
 * @Author Guo Huaijian
 * @Date 2020/5/17 18:49
 */
public interface SysMenuService extends IService<SysMenu> {


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
