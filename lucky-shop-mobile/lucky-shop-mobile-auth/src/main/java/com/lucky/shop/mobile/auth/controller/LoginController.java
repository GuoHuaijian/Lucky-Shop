package com.lucky.shop.mobile.auth.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.system.UserInfo;
import com.alibaba.fastjson.JSONObject;
import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.common.core.tool.Maps;
import com.lucky.shop.common.core.utils.MD5;
import com.lucky.shop.common.core.utils.RandomUtil;
import com.lucky.shop.mobile.auth.domain.ShopUser;
import com.lucky.shop.mobile.auth.service.ShopUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Guo Huaijian
 * @Date 2020/6/22 16:36
 */
@RestController
@RequestMapping("/")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Value("${auth.oauth2.access-token-url}")
    public String accessTokenUrl;

    @Value("${auth.oauth2.grant_type}")
    public String oauth2GrantType;

    @Value("${auth.oauth2.client_id}")
    public String oauth2ClientId;

    @Value("${auth.oauth2.client_secret}")
    public String oauth2ClientSecret;

    @Autowired
    private ShopUserService shopUserService;

    @Autowired
    private TokenStore tokenStore;

    /**
     * 发送短信
     *
     * @param mobile
     * @return
     */
    @PostMapping(value = "sendSmsCode")
    public ResponseResult sendSmsCode(@RequestParam String mobile) {
        shopUserService.sendSmsCode(mobile);
        return ResponseResult.success();
    }

    /**
     * 使用手机号和短信验证码登录或者注册
     *
     * @param mobile
     * @param smsCode
     * @return
     */
    @PostMapping(value = "loginOrReg")
    public ResponseResult loginOrReg(@RequestParam String mobile, @RequestParam String smsCode) {
        try {
            logger.info("用户登录:" + mobile + ",短信验证码:" + smsCode);
            //1,
            ShopUser user = shopUserService.findByMobile(mobile);
            Boolean validateRet = shopUserService.validateSmsCode(mobile, smsCode);

            Map<String, Object> result = new HashMap<>(6);
            if (validateRet) {
                if (user == null) {
                    //初始化6位密码
                    String initPassword = RandomUtil.getRandomString(6);
                    user = shopUserService.register(mobile, initPassword);
                    result.put("initPassword", initPassword);

                    // 通过 HTTP 获取token
                    Map<String, Object> params = Maps.newHashMap();
                    params.put("username", user.getMobile());
                    params.put("password", user.getPassword());
                    params.put("grant_type", oauth2GrantType);
                    params.put("client_id", oauth2ClientId);
                    params.put("client_secret", oauth2ClientSecret);

                    try {
                        // 解析响应结果封装并返回
                        String jsonString = HttpUtil.post(accessTokenUrl, params);
                        Map<String, Object> jsonMap = (Map) JSONObject.parse(jsonString);
                        String token = String.valueOf(jsonMap.get("access_token"));
                        user.setLastLoginTime(new Date());
                        shopUserService.updateById(user);
                        UserInfo userInfo = new UserInfo();
                        BeanUtils.copyProperties(user, userInfo);
                        result.put("user", userInfo);
                        logger.info("token:{}", token);
                        result.put("token", token);
                        return ResponseResult.success(result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return ResponseResult.error("短信验证码错误");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ResponseResult.error("登录时失败");
    }

    /**
     * 使用手机号和密码登录
     *
     * @param mobile
     * @param password
     * @return
     */
    @PostMapping(value = "loginByPass")
    public ResponseResult loginByPass(@RequestParam String mobile, @RequestParam String password) {
        try {
            logger.info("用户登录:" + mobile + ",密码:" + password);
            //1,
            ShopUser user = shopUserService.findByMobile(mobile);
            if (user == null) {
                return ResponseResult.error("该用户不存在");
            }

            String passwdMd5 = MD5.md5(password, user.getSalt());
            //2,
            if (!user.getPassword().equals(passwdMd5)) {
                return ResponseResult.error("输入的密码错误");
            }
            // 通过 HTTP 获取token
            Map<String, Object> params = Maps.newHashMap();
            params.put("username", user.getMobile());
            params.put("password", user.getPassword());
            params.put("grant_type", oauth2GrantType);
            params.put("client_id", oauth2ClientId);
            params.put("client_secret", oauth2ClientSecret);

            try {
                // 解析响应结果封装并返回
                String jsonString = HttpUtil.post(accessTokenUrl, params);
                Map<String, Object> jsonMap = (Map) JSONObject.parse(jsonString);
                String token = String.valueOf(jsonMap.get("access_token"));
                Map<String, Object> result = new HashMap<>(1);
                user.setLastLoginTime(new Date());
                shopUserService.updateById(user);
                UserInfo userInfo = new UserInfo();
                BeanUtils.copyProperties(user, userInfo);
                logger.info("token:{}", token);
                result.put("token", token);
                result.put("user", userInfo);
                return ResponseResult.success(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ResponseResult.error("登录时失败");
    }

    /**
     * 退出登录
     *
     * @return
     */
    @PostMapping(value = "/logout")
    public ResponseResult logout(HttpServletRequest request) {
        // 获取 token
        String token = request.getHeader("Authorization");
        // 删除 token 以注销
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return ResponseResult.success();
    }
}
