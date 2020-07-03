package com.lucky.shop.admin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lucky.shop.admin.system.domain.SysMenu;
import com.lucky.shop.admin.system.domain.vo.SysMenuVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.List;

/**
 * 菜单
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 19:28
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据权限获取菜单
     *
     * @param roleIds
     * @return
     */
    List<SysMenuVO> getMenusByRoleids(@Param("roleIds") String roleIds);

    /**
     * 获取菜单
     *
     * @return
     */
    List getMenus();

    /**
     * 获取菜单id
     *
     * @param roleId
     * @return
     */
    List getMenuIdsByRoleId(@DefaultValue("1") Integer roleId);

    /**
     * 获取菜单树
     *
     * @return
     */
    List menuTreeList();

    /**
     * 根据菜单id获取菜单树
     *
     * @param menuIds
     * @return
     */
    List menuTreeListByMenuIds(@Param("menuIds") List<Long> menuIds);
}
