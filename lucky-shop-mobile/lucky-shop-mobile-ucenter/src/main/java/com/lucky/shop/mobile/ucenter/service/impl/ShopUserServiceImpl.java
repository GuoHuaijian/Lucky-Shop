package com.lucky.shop.mobile.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.common.core.utils.HttpUtil;
import com.lucky.shop.common.core.utils.MD5;
import com.lucky.shop.common.core.utils.RandomUtil;
import com.lucky.shop.common.core.utils.StringUtil;
import com.lucky.shop.mobile.ucenter.domain.ShopUser;
import com.lucky.shop.mobile.ucenter.domain.vo.UserInfo;
import com.lucky.shop.mobile.ucenter.mapper.ShopUserMapper;
import com.lucky.shop.mobile.ucenter.service.ShopUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 用户
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 0:46
 */
@Service
public class ShopUserServiceImpl extends ServiceImpl<ShopUserMapper, ShopUser> implements ShopUserService {

    @Autowired
    private WeixinService weixinService;

    /**
     * 获取用户信息
     *
     * @return
     */
    @Override
    public ShopUser getInfo() {
        ShopUser shopUser = getCurrentUser();
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(shopUser, userInfo);
        return shopUser;
    }

    /**
     * 修改用户信息
     *
     * @param userName
     * @return
     */
    @Override
    public ShopUser updateUserName(String userName) {
        ShopUser user = getCurrentUser();
        user.setNickName(userName);
        this.updateById(user);
        return user;
    }

    /**
     * 修改性别
     *
     * @param gender
     * @return
     */
    @Override
    public ShopUser updateGender(String gender) {
        ShopUser user = getCurrentUser();
        user.setGender(gender);
        this.updateById(user);
        return user;
    }

    /**
     * 修改密码
     *
     * @param oldPwd
     * @param password
     * @param rePassword
     * @return
     */
    @Override
    public String updatePassword(String oldPwd, String password, String rePassword) {
        if (StringUtil.isEmpty(oldPwd) || StringUtil.isEmpty(password) || StringUtil.isEmpty(rePassword)) {
            return "项目并能为空";
        }
        if (!StringUtil.equals(password, rePassword)) {
            return "密码前后不一致";
        }
        ShopUser user = getCurrentUser();
        String oldPasswdMd5 = MD5.md5(oldPwd, user.getSalt());
        if (!StringUtil.equals(oldPasswdMd5, user.getPassword())) {
            return "旧密码不正确";
        }
        user.setPassword(MD5.md5(password, user.getSalt()));
        this.updateById(user);
        return null;
    }

    /**
     * 发送验证码
     *
     * @param mobile
     * @return
     */
    @Override
    public String sendSmsCode(String mobile) {
        String smsCode = this.sendSmsCodeForOldMobile(mobile);
        // TODO 测试环境直接返回验证码，生成环境切忌返回该验证码
        return smsCode;
    }

    /**
     * 获取微信openid
     *
     * @param code
     * @param request
     * @return
     */
    @Override
    public Object getWxOpenId(String code, HttpServletRequest request) {
        ShopUser user = getCurrentUser();
        boolean wxAuth = weixinService.isAuth(user, code);
        return wxAuth ? null : "获取openid失败";
    }

    /**
     * 获取微信信息
     *
     * @param url
     * @return
     */
    @Override
    public Map getWxSign(String url) {
        Map<String, String> map = weixinService.getSign(url);
        return map;
    }

    /**
     * 获取微信token
     */
    @Override
    public void updateWxToken() {
        weixinService.updateWeixinToken();
    }

    /**
     * 通过登录账号获取用户
     *
     * @param account
     * @return
     */
    @Override
    public ShopUser getUserByAccount(String account) {
        QueryWrapper<ShopUser> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopUser.COL_MOBILE, account);
        return this.getOne(wrapper);
    }

    private ShopUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String account = authentication.getName();
        QueryWrapper<ShopUser> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopUser.COL_MOBILE, account);
        ShopUser user = this.getOne(wrapper);
        return user;
    }

    private String sendSmsCodeForOldMobile(String mobile) {
        //todo 发送短信验证码逻辑，暂不实现
        String smsCode = RandomUtil.getRandomNumber(4);
        HttpUtil.getRequest().getSession().setAttribute(mobile + "_smsCode", smsCode);
        return smsCode;
    }
}
