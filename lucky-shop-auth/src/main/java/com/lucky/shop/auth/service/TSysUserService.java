package com.lucky.shop.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.auth.domain.AuthorizationUser;
import com.lucky.shop.auth.domain.TSysUser;

/**
 * 用户Service
 *
 * @Author Guo Huaijian
 * @Date 2020/5/16 21:42
 */
public interface TSysUserService extends IService<TSysUser> {

    /**
     * 获取用户菜单，角色等相关信息
     *
     * @param account
     * @return
     */
    AuthorizationUser getAuthorizationInfo(String account);

}
