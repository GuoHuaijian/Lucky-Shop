package com.lucky.shop.admin.auth.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.auth.domain.TSysMenu;
import com.lucky.shop.admin.auth.mapper.TSysMenuMapper;
import com.lucky.shop.admin.auth.service.TSysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单ServiceImpl
 * @Author Guo Huaijian
 * @Date 2020/5/17 18:49
 */
@Service
public class TSysMenuServiceImpl extends ServiceImpl<TSysMenuMapper, TSysMenu> implements TSysMenuService {

    @Autowired
    private TSysMenuService menuService;

    /**
     * 查询菜单地址
     * @param roleId
     * @return
     */
    @Override
    public List<String> getResUrlsByRoleId(Long roleId) {
        return menuService.getResUrlsByRoleId(roleId);
    }

    /**
     * 查询菜单编号
     * @param roleId
     * @return
     */
    @Override
    public List<String> getResCodesByRoleId(Long roleId) {
        return menuService.getResCodesByRoleId(roleId);
    }
}
