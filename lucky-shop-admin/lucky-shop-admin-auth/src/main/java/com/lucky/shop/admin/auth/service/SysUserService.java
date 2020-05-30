package com.lucky.shop.admin.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.admin.auth.domain.AuthorizationUser;
import com.lucky.shop.admin.auth.domain.SysUser;

/**
 * 用户Service
 *
 * @Author Guo Huaijian
 * @Date 2020/5/16 21:42
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 获取用户菜单，角色等相关信息
     * @param account
     * @return
     */
    AuthorizationUser getAuthorizationInfo(String account);

}
