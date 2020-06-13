package com.lucky.shop.admin.system.service;

import com.lucky.shop.admin.system.domain.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 账号
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 18:04
 */
public interface SysUserService extends IService<SysUser> {


    /**
     * 根据登录账号获取用户信息
     *
     * @param account
     * @return
     */
    SysUser getUserByAccount(String account);
}
