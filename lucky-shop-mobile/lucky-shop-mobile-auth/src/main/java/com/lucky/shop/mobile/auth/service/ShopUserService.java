package com.lucky.shop.mobile.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.mobile.auth.domain.ShopUser;

/**
 * 用户
 *
 * @Author Guo Huaijian
 * @Date 2020/6/22 16:36
 */
public interface ShopUserService extends IService<ShopUser> {

    /**
     * 模拟发送短信
     *
     * @param mobile
     * @return
     */
    boolean sendSmsCode(String mobile);

    /**
     * 验证
     *
     * @param mobile
     * @param smsCode
     * @return
     */
    Boolean validateSmsCode(String mobile, String smsCode);

    /**
     * 通过手机号查询用户
     *
     * @param mobile
     * @return
     */
    ShopUser findByMobile(String mobile);

    /**
     * 注册
     *
     * @param mobile
     * @param initPwd
     * @return
     */
    ShopUser register(String mobile, String initPwd);
}
