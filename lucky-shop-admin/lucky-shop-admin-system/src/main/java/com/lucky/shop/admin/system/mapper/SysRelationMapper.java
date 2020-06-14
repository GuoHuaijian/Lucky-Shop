package com.lucky.shop.admin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lucky.shop.admin.system.domain.SysRelation;

/**
 * 菜单角色关系
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 22:50
 */
public interface SysRelationMapper extends BaseMapper<SysRelation> {

    /**
     * 删除权限
     *
     * @param roleId
     * @return
     */
    int deleteByRoleId(Long roleId);
}
