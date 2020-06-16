package com.lucky.shop.admin.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lucky.shop.admin.system.domain.SysRole;
import com.lucky.shop.admin.system.domain.SysUser;
import com.lucky.shop.admin.system.service.SysRoleService;
import com.lucky.shop.admin.system.service.SysUserService;
import com.lucky.shop.common.core.constant.Const;
import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.common.core.utils.BeanUtil;
import com.lucky.shop.common.core.warpper.RoleWarpper;
import com.lucky.shop.common.log.annotation.BussinessLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 角色
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 23:19
 */
@Slf4j
@RestController
@RequestMapping("system/role")
public class SysRoleController {

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysUserService userService;

    /**
     * 角色列表
     *
     * @param name
     * @return
     */
    @GetMapping(value = "/list")
//    @RequiresPermissions(value = {Permission.ROLE})
    public ResponseResult list(String name) {
        List<SysRole> roles = roleService.list(name);
        // TODO 待完善
        return ResponseResult.success(new RoleWarpper(BeanUtil.objectsToMaps(roles)).warp());
    }

    /**
     * 编辑角色
     *
     * @param role
     * @return
     */
    @PostMapping()
    @BussinessLog(value = "编辑角色", key = "name")
//    @RequiresPermissions(value = {Permission.ROLE_EDIT})
    public ResponseResult save(@Valid SysRole role) {
        roleService.saveRole(role);
        return ResponseResult.success();
    }

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    @DeleteMapping()
    @BussinessLog(value = "删除角色", key = "roleId")
//    @RequiresPermissions(value = {Permission.ROLE_DEL})
    public ResponseResult remove(@RequestParam Long roleId) {
        log.info("id:{}", roleId);
        if (roleId == null) {
            throw new RuntimeException();
        }
        if (roleId.intValue() < 2) {
            return ResponseResult.error("不能删除初始角色");
        }
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq(SysUser.COL_ROLEID, String.valueOf(roleId));
        List<SysUser> userList = userService.list(wrapper);
        if (!userList.isEmpty()) {
            return ResponseResult.error("有用户使用该角色，禁止删除");
        }
        //不能删除超级管理员角色
        if (roleId.intValue() == Const.ADMIN_ROLE_ID) {
            return ResponseResult.error("禁止删除超级管理员角色");
        }
        //缓存被删除的角色名称
        // TODO 待完善
//        LogObjectHolder.me().set(ConstantFactory.me().getSingleRoleName(roleId));
        roleService.remove(roleId);
        return ResponseResult.success();
    }

    /**
     * 配置角色权限
     *
     * @param roleId
     * @param permissions
     * @return
     */
    @GetMapping(value = "/savePermisson")
    @BussinessLog(value = "配置角色权限", key = "roleId")
//    @RequiresPermissions(value = {Permission.ROLE_EDIT})
    public ResponseResult setAuthority(Long roleId, String permissions) {
        if (roleId == null) {
            throw new RuntimeException();
        }
        roleService.setAuthority(roleId, permissions);
        return ResponseResult.success();
    }


    /**
     * 获取角色树
     *
     * @param idUser
     * @return
     */
    @GetMapping(value = "/roleTreeListByIdUser")
//    @RequiresPermissions(value = {Permission.ROLE})
    public ResponseResult roleTreeListByIdUser(Long idUser) {
        Map map = roleService.roleTreeListByIdUser(idUser);
        return ResponseResult.success(map);
    }
}
