package com.lucky.shop.admin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.admin.system.domain.SysRole;

import java.util.List;
import java.util.Map;

/**
 * 角色
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 22:09
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 角色列表
     *
     * @param name
     * @return
     */
    List<SysRole> list(String name);

    /**
     * 编辑角色
     *
     * @param role
     */
    void saveRole(SysRole role);

    /**
     * 删除角色
     *
     * @param roleId
     */
    void remove(Long roleId);

    /**
     * 配置角色权限
     *
     * @param roleId
     * @param permissions
     */
    void setAuthority(Long roleId, String permissions);

    /**
     * 获取角色树
     *
     * @param idUser
     * @return
     */
    Map roleTreeListByIdUser(Long idUser);

}
