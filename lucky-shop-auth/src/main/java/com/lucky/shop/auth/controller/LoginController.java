package com.lucky.shop.auth.controller;

import com.lucky.shop.auth.service.TSysUserService;
import com.lucky.shop.common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登录
 *
 * @Author Guo Huaijian
 * @Date 2020/5/16 21:42
 */
@RestController
public class LoginController {

    @Autowired
    private TSysUserService userService;

    /**
     * 登录
     * @return
     */
    public ResponseResult login(){

        return null;
    }

}
