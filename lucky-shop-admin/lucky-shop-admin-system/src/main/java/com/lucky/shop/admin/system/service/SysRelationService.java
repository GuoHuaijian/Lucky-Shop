package com.lucky.shop.admin.system.service;

import com.lucky.shop.admin.system.domain.SysRelation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 菜单角色关系
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 22:50
 */
public interface SysRelationService extends IService<SysRelation> {

    /**
     * 删除权限
     *
     * @param roleId
     * @return
     */
    int deleteByRoleId(Long roleId);


}
