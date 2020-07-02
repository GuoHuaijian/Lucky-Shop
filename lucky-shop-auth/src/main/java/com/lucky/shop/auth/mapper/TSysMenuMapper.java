package com.lucky.shop.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lucky.shop.auth.domain.TSysMenu;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 菜单mapper
 *
 * @Author Guo Huaijian
 * @Date 2020/5/17 18:49
 */
public interface TSysMenuMapper extends BaseMapper<TSysMenu> {

    /**
     * 查询菜单地址
     *
     * @param roleId
     * @return
     */
    @Select(value = "select url from t_sys_relation rel inner join t_sys_menu m on rel.menuid = m.id " + "where m" + ".status=1 and  rel.roleid = #{roleId}")
    List<String> getResUrlsByRoleId(Long roleId);

    /**
     * 查询菜单编号
     *
     * @param roleId
     * @return
     */
    @Select(value = "select code from t_sys_relation rel inner join t_sys_menu m on rel.menuid = m.id" + " where m.status=1 and  rel.roleid = #{roleId}")
    List<String> getResCodesByRoleId(Long roleId);
}
