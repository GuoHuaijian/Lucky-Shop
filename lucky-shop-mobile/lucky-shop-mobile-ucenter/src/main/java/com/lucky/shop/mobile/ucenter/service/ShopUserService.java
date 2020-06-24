package com.lucky.shop.mobile.ucenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.mobile.ucenter.domain.ShopUser;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 用户
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 0:46
 */
public interface ShopUserService extends IService<ShopUser> {

    /**
     * 获取用户信息
     *
     * @return
     */
    ShopUser getInfo();

    /**
     * 修改用户信息
     *
     * @param userName
     * @return
     */
    ShopUser updateUserName(String userName);

    /**
     * 修改性别
     *
     * @param gender
     * @return
     */
    ShopUser updateGender(String gender);

    /**
     * 修改密码
     *
     * @param oldPwd
     * @param password
     * @param rePassword
     * @return
     */
    String updatePassword(String oldPwd, String password, String rePassword);

    /**
     * 发送验证码
     *
     * @param mobile
     * @return
     */
    String sendSmsCode(String mobile);

    /**
     * 获取微信openid
     *
     * @param code
     * @param request
     * @return
     */
    Object getWxOpenId(String code, HttpServletRequest request);

    /**
     * 获取微信信息
     *
     * @param url
     * @return
     */
    Map getWxSign(String url);

    /**
     * 获取微信token，
     * todo 该方法仅用作测试,微信token会通过后台管理中的定时任务定时更新
     *
     * @return
     */
    void updateWxToken();

    /**
     * 通过登录账号获取用户
     *
     * @param account
     * @return
     */
    ShopUser getUserByAccount(String account);

}
