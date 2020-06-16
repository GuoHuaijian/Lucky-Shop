package com.lucky.shop.admin.system.controller;

import com.lucky.shop.admin.system.domain.SysMenu;
import com.lucky.shop.admin.system.domain.vo.MenuNode;
import com.lucky.shop.admin.system.domain.vo.RouterMenu;
import com.lucky.shop.admin.system.service.SysMenuService;
import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.common.log.annotation.BussinessLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 菜单
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 21:42
 */
@Slf4j
@RestController
@RequestMapping("system/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService menuService;

    /**
     * 获取菜单地址列表
     *
     * @return
     */
    @GetMapping(value = "/listForRouter")
    public ResponseResult listForRouter() {
        List<RouterMenu> list = menuService.listForRouter();
        return ResponseResult.success(list);
    }

    /**
     * 获取菜单节点列表
     *
     * @return
     */
    @GetMapping(value = "/list")
//    @RequiresPermissions(value = {Permission.MENU})
    public ResponseResult list() {
        List<MenuNode> list = menuService.MenuNodeList();
        return ResponseResult.success(list);
    }

    /**
     * 编辑菜单
     *
     * @param menu
     * @return
     */
    @PostMapping()
    @BussinessLog(value = "编辑菜单", key = "name")
//    @RequiresPermissions(value = {Permission.MENU_EDIT})
    public ResponseResult save(@ModelAttribute @Valid SysMenu menu) {
        menuService.saveMenu(menu);
        return ResponseResult.success();
    }

    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    @DeleteMapping()
    @BussinessLog(value = "删除菜单", key = "id")
//    @RequiresPermissions(value = {Permission.MENU_DEL})
    public Object remove(@RequestParam Long id) {
        log.info("id:{}", id);
        if (id == null) {
            throw new RuntimeException();
        }
        //演示环境不允许删除初始化的菜单
        if (id.intValue() < 70) {
            return ResponseResult.error("演示环境不允许删除初始菜单");
        }
        menuService.remove(id);
        return ResponseResult.success();
    }

    /**
     * 获取菜单树
     *
     * @param roleId
     * @return
     */
    @GetMapping(value = "/menuTreeListByRoleId")
//    @RequiresPermissions(value = {Permission.MENU})
    public ResponseResult menuTreeListByRoleId(Integer roleId) {
        Map map = menuService.menuTreeListByRoleId(roleId);
        return ResponseResult.success(map);
    }
}
