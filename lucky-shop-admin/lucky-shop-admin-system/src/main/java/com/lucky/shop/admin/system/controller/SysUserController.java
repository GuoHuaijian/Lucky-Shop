package com.lucky.shop.admin.system.controller;

import com.lucky.shop.admin.system.domain.SysUser;
import com.lucky.shop.admin.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账号
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 18:06
 */
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService userService;

    /**
     * 根据登录账号获取用户信息
     *
     * @param account
     * @return
     */
    @GetMapping("userInfo/{account}")
    public SysUser getUserByAccount(@PathVariable String account) {
        SysUser sysUser = userService.getUserByAccount(account);
        return sysUser;
    }

}
