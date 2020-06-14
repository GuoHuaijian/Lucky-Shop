package com.lucky.shop.admin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.system.domain.AuthorizationUser;
import com.lucky.shop.admin.system.domain.SysMenu;
import com.lucky.shop.admin.system.domain.vo.*;
import com.lucky.shop.admin.system.mapper.SysMenuMapper;
import com.lucky.shop.admin.system.service.SysMenuService;
import com.lucky.shop.common.core.enums.MenuStatus;
import com.lucky.shop.common.core.tool.LogObjectHolder;
import com.lucky.shop.common.core.tool.Maps;
import com.lucky.shop.common.core.utils.HttpUtil;
import com.lucky.shop.common.core.utils.Lists;
import com.lucky.shop.common.core.utils.StringUtil;
import com.lucky.shop.common.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 菜单
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 19:28
 */
@Slf4j
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper menuMapper;

    @Autowired
    private RedisService redisService;

    /**
     * 获取菜单地址列表
     *
     * @return
     */
    @Override
    public List<RouterMenu> listForRouter() {
        AuthorizationUser shiroUser = redisService.getCacheObject(HttpUtil.getToken());
        List<RouterMenu> list = this.getSideBarMenus(shiroUser.getRoleList());
        return list;
    }

    /**
     * 获取菜单节点列表
     *
     * @return
     */
    @Override
    public List<MenuNode> MenuNodeList() {
        List<MenuNode> list = this.getMenus();
        return list;
    }

    /**
     * 编辑菜单
     *
     * @param menu
     */
    @Override
    public void saveMenu(SysMenu menu) {
        // 判断是否存在该编号
        if (menu.getId() == null) {
            // TODO 待完善
//            String existedMenuName = ConstantFactory.me().getMenuNameByCode(menu.getCode());
//            if (StringUtil.isNotEmpty(existedMenuName)) {
//                throw new RuntimeException();
//            }
            menu.setStatus(MenuStatus.ENABLE.getCode());
        }

        //设置父级菜单编号
        this.menuSetPcode(menu);
        if (menu.getId() == null) {
            this.save(menu);
        } else {
            SysMenu old = this.getById(menu.getId());
            LogObjectHolder.me().set(old);
            this.updateById(menu);
        }
    }

    /**
     * 删除菜单
     *
     * @param id
     */
    @Override
    public void remove(Long id) {
        // 缓存菜单的名称
        // TODO 待完善
//        LogObjectHolder.me().set(ConstantFactory.me().getMenuName(id));
        this.delMenuContainSubMenus(id);
    }

    /**
     * 获取菜单树
     *
     * @param roleId
     * @return
     */
    @Override
    public Map menuTreeListByRoleId(Integer roleId) {
        List<Long> menuIds = this.getMenuIdsByRoleId(roleId);
        List<ZTreeNode> roleTreeList = null;
        if (menuIds == null || menuIds.isEmpty()) {
            roleTreeList = this.menuTreeList(null);
        } else {
            roleTreeList = this.menuTreeList(menuIds);

        }
        List<Node> list = this.generateMenuTreeForRole(roleTreeList);

        //element-ui中tree控件中如果选中父节点会默认选中所有子节点，所以这里将所有非叶子节点去掉
        Map<Long, ZTreeNode> map = Lists.toMap(roleTreeList, "id");
        Map<Long, List<ZTreeNode>> group = Lists.group(roleTreeList, "pId");
        for (Map.Entry<Long, List<ZTreeNode>> entry : group.entrySet()) {
            if (entry.getValue().size() > 1) {
                roleTreeList.remove(map.get(entry.getKey()));
            }
        }

        List<Long> checkedIds = com.google.common.collect.Lists.newArrayList();
        for (ZTreeNode zTreeNode : roleTreeList) {
            if (zTreeNode.getChecked() != null && zTreeNode.getChecked()
                    && zTreeNode.getpId().intValue() != 0) {
                checkedIds.add(zTreeNode.getId());
            }
        }
        return Maps.newHashMap("treeData", list, "checkedIds", checkedIds);
    }

    /**
     * 获取左侧菜单树
     *
     * @return
     */
    public List<RouterMenu> getSideBarMenus(List<Long> roleIds) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < roleIds.size(); i++) {
            if (i == roleIds.size() - 1) {
                builder.append(roleIds.get(i));
            } else {
                builder.append(roleIds.get(i)).append(",");
            }
        }
        List<RouterMenu> list = transferRouteMenu(menuMapper.getMenusByRoleids(builder.toString()));
        List<RouterMenu> result = generateRouterTree(list);
        for (RouterMenu menuNode : result) {
            if (!menuNode.getChildren().isEmpty()) {
                sortRouterTree(menuNode.getChildren());
                for (RouterMenu menuNode1 : menuNode.getChildren()) {
                    if (!menuNode1.getChildren().isEmpty()) {
                        sortRouterTree(menuNode1.getChildren());
                    }
                }
            }
        }
        sortRouterTree(result);
        return result;
    }

    private List<RouterMenu> transferRouteMenu(List menus) {
        List<RouterMenu> routerMenus = new ArrayList<>();
        try {
            for (int i = 0; i < menus.size(); i++) {
                Object[] source = (Object[]) menus.get(i);
                if (source[10] == null) {
                    continue;
                }

                RouterMenu menu = new RouterMenu();
                menu.setPath(String.valueOf(source[4]));
                menu.setName(String.valueOf(source[3]));
                MenuMeta meta = new MenuMeta();
                meta.setIcon(String.valueOf(source[1]));
                // 如果使用前端vue-i18n对菜单进行国际化，则title設置为code，且code需要与国际化资源文件中的key值一致
                meta.setTitle(String.valueOf(source[8]));
                // 如果不需要做国际化，则title直接设置后台管理配置的菜单标题即可
                // meta.setTitle(String.valueOf(source[3]));
                menu.setNum(Integer.valueOf(source[7].toString()));
                menu.setParentId(Long.valueOf(source[2].toString()));
                menu.setComponent(source[10].toString());
                menu.setId(Long.valueOf(source[0].toString()));
                menu.setMeta(meta);
                if ("1".equals(source[11].toString())) {
                    menu.setHidden(true);
                }
                routerMenus.add(menu);

            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return routerMenus;
    }

    private List<RouterMenu> generateRouterTree(List<RouterMenu> list) {
        List<RouterMenu> result = new ArrayList<>(20);
        Map<Long, RouterMenu> map = Lists.toMap(list, "id");
        for (Map.Entry<Long, RouterMenu> entry : map.entrySet()) {
            RouterMenu menuNode = entry.getValue();

            if (menuNode.getParentId().intValue() != 0) {
                RouterMenu parentNode = map.get(menuNode.getParentId());
                if (parentNode != null) {
                    parentNode.getChildren().add(menuNode);
                }
            } else {
                result.add(menuNode);
            }
        }
        return result;

    }

    private void sortRouterTree(List<RouterMenu> list) {
        Collections.sort(list, new Comparator<RouterMenu>() {
            @Override
            public int compare(RouterMenu o1, RouterMenu o2) {
                return o1.getNum() - o2.getNum();
            }
        });
    }

    /**
     * 获取菜单列表
     *
     * @return
     */
    public List<MenuNode> getMenus() {
        List<MenuNode> list = transferMenuNode(menuMapper.getMenus());
        List<MenuNode> result = generateTree(list);
        for (MenuNode menuNode : result) {
            if (!menuNode.getChildren().isEmpty()) {
                sortTree(menuNode.getChildren());
                for (MenuNode menuNode1 : menuNode.getChildren()) {
                    if (!menuNode1.getChildren().isEmpty()) {
                        sortTree(menuNode1.getChildren());
                    }
                }
            }
        }
        sortTree(result);
        return result;
    }

    private List<MenuNode> transferMenuNode(List menus) {
        List<MenuNode> menuNodes = new ArrayList<>();
        try {
            for (int i = 0; i < menus.size(); i++) {
                Object[] source = (Object[]) menus.get(i);
                MenuNode menuNode = new MenuNode();
                menuNode.setId(Long.valueOf(source[0].toString()));
                menuNode.setIcon(String.valueOf(source[1]));
                menuNode.setParentId(Long.valueOf(source[2].toString()));
                menuNode.setName(String.valueOf(source[3]));
                menuNode.setUrl(String.valueOf(source[4]));
                menuNode.setLevels(Integer.valueOf(source[5].toString()));
                menuNode.setIsmenu(Integer.valueOf(source[6].toString()));
                menuNode.setNum(Integer.valueOf(source[7].toString()));
                menuNode.setCode(String.valueOf(source[8]));
                menuNode.setStatus(Integer.valueOf(source[9].toString()));
                if (source[10] != null) {
                    menuNode.setComponent(source[10].toString());
                }
                if ("1".equals(source[11].toString())) {
                    menuNode.setHidden(true);
                } else {
                    menuNode.setHidden(false);
                }
                menuNodes.add(menuNode);

            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return menuNodes;
    }

    private List<MenuNode> generateTree(List<MenuNode> list) {
        List<MenuNode> result = new ArrayList<>(20);
        Map<Long, MenuNode> map = Lists.toMap(list, "id");
        for (Map.Entry<Long, MenuNode> entry : map.entrySet()) {
            MenuNode menuNode = entry.getValue();

            if (menuNode.getParentId().intValue() != 0) {
                MenuNode parentNode = map.get(menuNode.getParentId());
                parentNode.getChildren().add(menuNode);
            } else {
                result.add(menuNode);
            }
        }
        return result;

    }

    private void sortTree(List<MenuNode> list) {
        Collections.sort(list, new Comparator<MenuNode>() {
            @Override
            public int compare(MenuNode o1, MenuNode o2) {
                return o1.getNum() - o2.getNum();
            }
        });
    }

    public void menuSetPcode(SysMenu menu) {
        if (StringUtil.isEmpty(menu.getPcode()) || "0".equals(menu.getPcode())) {
            menu.setPcode("0");
            menu.setPcodes("[0],");
            menu.setLevels(1);
        } else {
            QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
            wrapper.eq(SysMenu.COL_CODE, menu.getCode());
            SysMenu pMenu = this.getOne(wrapper);
            Integer pLevels = pMenu.getLevels();
            menu.setPcode(pMenu.getCode());

            // 如果编号和父编号一致会导致无限递归
            if (menu.getCode().equals(menu.getPcode())) {
                throw new RuntimeException();
            }
            menu.setLevels(pLevels + 1);
            menu.setPcodes(pMenu.getPcodes() + "[" + pMenu.getCode() + "],");
        }
    }

    public void delMenuContainSubMenus(Long menuId) {
        SysMenu menu = getById(menuId);
        // 删除所有子菜单
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        wrapper.like(SysMenu.COL_PCODES, menu.getCode());
        List<SysMenu> menus = this.list(wrapper);
        this.removeByIds(menus);
        // 删除当前菜单
        this.remove(menuId);
    }

    public List<Long> getMenuIdsByRoleId(Integer roleId) {
        return menuMapper.getMenuIdsByRoleId(roleId);
    }

    public List<ZTreeNode> menuTreeList(List<Long> menuIds) {
        List<ZTreeNode> nodes = new ArrayList<>();
        if (menuIds == null) {
            List list = menuMapper.menuTreeList();

            for (int i = 0; i < list.size(); i++) {
                Object[] source = (Object[]) list.get(i);
                ZTreeNode node = new ZTreeNode();
                node.setId(Long.valueOf(source[0].toString()));
                node.setpId(Long.valueOf(source[1].toString()));
                node.setName(source[2].toString());
                node.setIsOpen(Boolean.valueOf(source[3].toString()));
                nodes.add(node);
            }
        } else {
            nodes = menuTreeListByMenuIds(menuIds);
        }
        return nodes;
    }

    private List<ZTreeNode> menuTreeListByMenuIds(List<Long> menuIds) {
        List list = menuMapper.menuTreeListByMenuIds(menuIds);
        List<ZTreeNode> nodes = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Object[] source = (Object[]) list.get(i);
            ZTreeNode node = new ZTreeNode();
            node.setId(Long.valueOf(source[0].toString()));
            node.setpId(Long.valueOf(source[1].toString()));
            node.setName(source[2].toString());
            node.setIsOpen(Boolean.valueOf(source[3].toString()));
            node.setChecked(Boolean.valueOf(source[4].toString()));
            nodes.add(node);
        }
        return nodes;
    }

    public List<Node> generateMenuTreeForRole(List<ZTreeNode> list) {
        List<Node> nodes = new ArrayList<>(20);
        for (ZTreeNode menu : list) {
            Node permissionNode = new Node();
            permissionNode.setId(menu.getId());
            permissionNode.setName(menu.getName());
            permissionNode.setPid(menu.getpId());
            permissionNode.setChecked(menu.getChecked());
            nodes.add(permissionNode);
        }
        for (Node permissionNode : nodes) {
            for (Node child : nodes) {
                if (child.getPid().intValue() == permissionNode.getId().intValue()) {
                    permissionNode.getChildren().add(child);
                }
            }
        }
        List<Node> result = new ArrayList<>(20);
        for (Node node : nodes) {
            if (node.getPid().intValue() == 0) {
                result.add(node);
            }
        }
        return result;


    }
}
