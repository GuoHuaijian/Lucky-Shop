package com.lucky.shop.admin.auth.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.auth.domain.AuthorizationUser;
import com.lucky.shop.admin.auth.domain.SysRole;
import com.lucky.shop.admin.auth.domain.SysUser;
import com.lucky.shop.admin.auth.mapper.SysUserMapper;
import com.lucky.shop.admin.auth.service.SysDeptService;
import com.lucky.shop.admin.auth.service.SysRoleService;
import com.lucky.shop.admin.auth.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户ServiceImpl
 *
 * @Author Guo Huaijian
 * @Date 2020/5/16 21:42
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysDeptService deptService;

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysMenuServiceImpl menuService;

    /**
     * 获取用户菜单，角色等相关信息
     *
     * @param account
     * @return
     */
    @Override
    public AuthorizationUser getAuthorizationInfo(String account) {
        AuthorizationUser userBean = new AuthorizationUser();
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq(SysUser.COL_ACCOUNT, account);
        SysUser user = this.getOne(wrapper);
        userBean.setId(user.getId());
        userBean.setAccount(user.getAccount());
        userBean.setDeptId(user.getDeptid());
        userBean.setDeptName(deptService.getById(user.getDeptid()).getFullname());
        userBean.setName(user.getName());
        userBean.setPassword(user.getPassword());
        String str = user.getRoleid();
        String[] split = str.split(",");
        Long[] roleArray = Convert.toLongArray(split);
        List<Long> roleList = new ArrayList<Long>();
        List<String> roleNameList = new ArrayList<String>();
        List<String> roleCodeList = new ArrayList<String>();
        Set<String> permissions = new HashSet<String>();
        Set<String> resUrls = new HashSet<>();
        for (Long roleId : roleArray) {
            roleList.add(roleId);
            SysRole role = roleService.getById(roleId);
            roleNameList.add(role.getName());
            roleCodeList.add(role.getTips());
            permissions.addAll(menuService.getResCodesByRoleId(roleId));
            resUrls.addAll(menuService.getResUrlsByRoleId(roleId));

        }
        userBean.setRoleList(roleList);
        userBean.setRoleNames(roleNameList);
        userBean.setRoleCodes(roleCodeList);
        userBean.setPermissions(permissions);
        userBean.setUrls(resUrls);
        return userBean;
    }

}
