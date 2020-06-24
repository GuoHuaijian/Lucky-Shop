package com.lucky.shop.mobile.ucenter.controller;

import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.mobile.ucenter.domain.ShopUser;
import com.lucky.shop.mobile.ucenter.service.ShopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 用户
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 0:48
 */
@RestController
@RequestMapping("/user")
public class ShopUserController {

    @Autowired
    private ShopUserService userService;

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping(value = "/getInfo")
    public ResponseResult getInfo() {
        ShopUser info = userService.getInfo();
        return ResponseResult.success(info);
    }

    /**
     * 修改用户信息
     *
     * @param userName
     * @return
     */
    @PostMapping(value = "/updateUserName/{userName}")
    public ResponseResult updateUserName(@PathVariable("userName") String userName) {
        ShopUser user = userService.updateUserName(userName);
        return ResponseResult.success(user);
    }

    /**
     * 修改性别
     *
     * @param gender
     * @return
     */
    @PostMapping(value = "/updateGender/{gender}")
    public ResponseResult updateGender(@PathVariable("gender") String gender) {
        ShopUser user = userService.updateGender(gender);
        return ResponseResult.success(user);
    }

    /**
     * 修改密码
     *
     * @param oldPwd
     * @param password
     * @param rePassword
     * @return
     */
    @PostMapping(value = "/updatePassword/{oldPwd}/{password}/{rePassword}")
    public ResponseResult updatePassword(@PathVariable("oldPwd") String oldPwd,
                                         @PathVariable("password") String password,
                                         @PathVariable("rePassword") String rePassword) {
        userService.updatePassword(oldPwd, password, rePassword);
        return ResponseResult.success();
    }

    /**
     * 发送验证码
     *
     * @param mobile
     * @return
     */
    @PostMapping(value = "sendSmsCode")
    public ResponseResult sendSmsCode(@RequestParam String mobile) {
        String smsCode = userService.sendSmsCode(mobile);
        //todo 测试环境直接返回验证码，生成环境切忌返回该验证码
        return ResponseResult.success(smsCode);
    }

    /**
     * 获取微信openid
     *
     * @param code
     * @param request
     * @return
     */
    @PostMapping(value = "getWxOpenId")
    public ResponseResult getWxOpenId(String code, HttpServletRequest request) {
        Object wxOpenId = userService.getWxOpenId(code, request);
        return ResponseResult.success(wxOpenId);
    }

    /**
     * 获取微信信息
     *
     * @param url
     * @return
     */
    @PostMapping(value = "getWxSign")
    public ResponseResult getWxSign(@RequestParam("url") String url) {
        Map map = userService.getWxSign(url);
        return ResponseResult.success(map);
    }

    /**
     * 获取微信token，
     * TODO 该方法仅用作测试,微信token会通过后台管理中的定时任务定时更新
     *
     * @return
     */
    @GetMapping(value = "updateWxToken")
    public ResponseResult updateWxToken() {
        userService.updateWxToken();
        return ResponseResult.success();

    }

    /**
     * 通过登录账号获取用户
     *
     * @param account
     * @return
     */
    @GetMapping("/account/{account}")
    public ShopUser getUserByAccount(@PathVariable String account) {
        return userService.getUserByAccount(account);
    }
}
