package com.lucky.shop.auth.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.auth.domain.AuthorizationUser;
import com.lucky.shop.auth.domain.TSysRole;
import com.lucky.shop.auth.domain.TSysUser;
import com.lucky.shop.auth.mapper.TSysUserMapper;
import com.lucky.shop.auth.service.TSysDeptService;
import com.lucky.shop.auth.service.TSysRoleService;
import com.lucky.shop.auth.service.TSysUserService;
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
public class TSysUserServiceImpl extends ServiceImpl<TSysUserMapper, TSysUser> implements TSysUserService {

    @Autowired
    private TSysDeptService deptService;

    @Autowired
    private TSysRoleService roleService;

    @Autowired
    private TSysMenuServiceImpl menuService;

    /**
     * 获取用户菜单，角色等相关信息
     *
     * @param account
     * @return
     */
    @Override
    public AuthorizationUser getAuthorizationInfo(String account) {
        AuthorizationUser userBean = new AuthorizationUser();
        QueryWrapper<TSysUser> wrapper = new QueryWrapper<>();
        wrapper.eq(TSysUser.COL_ACCOUNT, account);
        TSysUser user = this.getOne(wrapper);
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
            TSysRole role = roleService.getById(roleId);
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
