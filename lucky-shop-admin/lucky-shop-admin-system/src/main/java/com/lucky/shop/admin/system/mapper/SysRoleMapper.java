package com.lucky.shop.admin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lucky.shop.admin.system.domain.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 22:09
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 获取权限树
     *
     * @return
     */
    List roleTreeList();

    /**
     * 根据权限id获取权限树
     *
     * @param ids
     * @return
     */
    List roleTreeListByRoleId(@Param("ids") Long[] ids);
}
