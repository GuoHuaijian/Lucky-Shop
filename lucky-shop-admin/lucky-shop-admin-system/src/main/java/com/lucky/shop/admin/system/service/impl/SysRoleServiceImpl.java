package com.lucky.shop.admin.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.lucky.shop.admin.system.domain.SysRelation;
import com.lucky.shop.admin.system.domain.SysRole;
import com.lucky.shop.admin.system.domain.SysUser;
import com.lucky.shop.admin.system.domain.vo.Node;
import com.lucky.shop.admin.system.domain.vo.ZTreeNode;
import com.lucky.shop.admin.system.mapper.SysRoleMapper;
import com.lucky.shop.admin.system.service.SysRelationService;
import com.lucky.shop.admin.system.service.SysRoleService;
import com.lucky.shop.admin.system.service.SysUserService;
import com.lucky.shop.common.core.tool.Convert;
import com.lucky.shop.common.core.tool.LogObjectHolder;
import com.lucky.shop.common.core.tool.Maps;
import com.lucky.shop.common.core.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 角色
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 22:09
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRelationService relationService;

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 角色列表
     *
     * @param name
     * @return
     */
    @Override
    public List<SysRole> list(String name) {
        List<SysRole> roles;
        if (Strings.isNullOrEmpty(name)) {
            roles = this.list();
        } else {
            QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
            wrapper.eq(SysRole.COL_NAME, name);
            roles = this.list(wrapper);
        }
        return roles;
    }

    /**
     * 编辑角色
     *
     * @param role
     */
    @Override
    public void saveRole(SysRole role) {
        if (role.getId() == null) {
            this.save(role);
        } else {
            SysRole old = this.getById(role.getId());
            LogObjectHolder.me().set(old);
            this.updateById(role);
        }
    }

    /**
     * 删除角色
     *
     * @param roleId
     */
    @Override
    public void remove(Long roleId) {
        this.remove(roleId);
    }

    /**
     * 配置角色权限
     *
     * @param roleId
     * @param permissions
     */
    @Override
    public void setAuthority(Long roleId, String permissions) {
        // 删除该角色所有的权限
        relationService.deleteByRoleId(roleId);
        // 添加新的权限
        for (Long id : Convert.toLongArray(true, Convert.toStrArray(",", permissions))) {
            SysRelation relation = new SysRelation();
            relation.setRoleid(roleId);
            relation.setMenuid(id);
            relationService.save(relation);
        }
    }

    /**
     * 获取角色树
     *
     * @param idUser
     * @return
     */
    @Override
    public Map roleTreeListByIdUser(Long idUser) {
        SysUser user = sysUserService.getById(idUser);
        String roleIds = user.getRoleid();
        List<ZTreeNode> roleTreeList = null;
        if (StringUtil.isEmpty(roleIds)) {
            roleTreeList = this.roleTreeList();
        } else {
            Long[] roleArray = Convert.toLongArray(",", roleIds);
            roleTreeList = this.roleTreeListByRoleId(roleArray);
        }
        List<Node> list = this.generateRoleTree(roleTreeList);
        List<Long> checkedIds = Lists.newArrayList();
        for (ZTreeNode zTreeNode : roleTreeList) {
            if (zTreeNode.getChecked() != null && zTreeNode.getChecked()) {
                checkedIds.add(zTreeNode.getId());
            }
        }
        return Maps.newHashMap("treeData", list, "checkedIds", checkedIds);
    }

    public List<ZTreeNode> roleTreeList() {
        List list = roleMapper.roleTreeList();
        List<ZTreeNode> treeNodes = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Object[] arr = (Object[]) list.get(i);
            ZTreeNode node = new ZTreeNode();
            node.setId(Long.valueOf(arr[0].toString()));
            node.setpId(Long.valueOf(arr[1].toString()));
            node.setName(arr[2].toString());
            node.setOpen(Boolean.valueOf(arr[3].toString()));
            treeNodes.add(node);
        }
        return treeNodes;
    }

    public List<ZTreeNode> roleTreeListByRoleId(Long[] ids) {
        List list = roleMapper.roleTreeListByRoleId(ids);
        List<ZTreeNode> treeNodes = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Object[] arr = (Object[]) list.get(i);
            ZTreeNode node = new ZTreeNode();
            node.setId(Long.valueOf(arr[0].toString()));
            node.setpId(Long.valueOf(arr[1].toString()));
            node.setName(arr[2].toString());
            node.setOpen(Boolean.valueOf(arr[3].toString()));
            node.setChecked(Boolean.valueOf(arr[4].toString()));
            treeNodes.add(node);
        }
        return treeNodes;
    }

    public List<Node> generateRoleTree(List<ZTreeNode> list) {
        List<Node> nodes = new ArrayList<>();
        for (ZTreeNode role : list) {
            Node roleNode = new Node();
            roleNode.setId(role.getId());
            roleNode.setName(role.getName());
            roleNode.setPid(role.getpId());
            roleNode.setChecked(role.getChecked());
            nodes.add(roleNode);
        }
        return nodes;
    }
}
