package com.lucky.shop.admin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.admin.system.domain.SysMenu;
import com.lucky.shop.admin.system.domain.vo.MenuNode;
import com.lucky.shop.admin.system.domain.vo.RouterMenu;

import java.util.List;
import java.util.Map;

/**
 * 菜单
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 19:28
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 获取菜单地址列表
     *
     * @return
     */
    List<RouterMenu> listForRouter();

    /**
     * 获取菜单节点列表
     *
     * @return
     */
    List<MenuNode> MenuNodeList();

    /**
     * 编辑菜单
     *
     * @param menu
     */
    void saveMenu(SysMenu menu);

    /**
     * 删除菜单
     *
     * @param id
     */
    void remove(Long id);

    /**
     * 获取菜单树
     *
     * @param roleId
     * @return
     */
    Map menuTreeListByRoleId(Integer roleId);

}
