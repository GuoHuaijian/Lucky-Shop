package com.lucky.shop.admin.mall.utils;


import com.lucky.shop.admin.system.api.RemoteSysUserService;
import com.lucky.shop.admin.system.api.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * 系统用户工具类
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 19:03
 */
@Service
public class SysUserUtils {

    @Autowired
    private RemoteSysUserService sysUserService;

    /**
     * 获取当前登录用户的名字
     *
     * @return
     */
    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String account = authentication.getName();
        SysUser sysUser = sysUserService.getUserByAccount(account);
        return sysUser.getName();
    }
}
