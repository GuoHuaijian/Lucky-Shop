package com.lucky.shop.admin.auth.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lucky.shop.admin.auth.domain.AuthorizationUser;
import com.lucky.shop.admin.auth.domain.SysUser;
import com.lucky.shop.admin.auth.service.SysUserService;
import com.lucky.shop.common.dto.ResponseResult;
import com.lucky.shop.common.tool.Maps;
import org.nutz.mapl.Mapl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户登录
 *
 * @Author Guo Huaijian
 * @Date 2020/5/16 21:42
 */
@RestController
public class LoginController {

    @Value("${auth.oauth2.access-token-url}")
    public String accessTokenUrl;

    @Value("${auth.oauth2.grant_type}")
    public String oauth2GrantType;

    @Value("${auth.oauth2.client_id}")
    public String oauth2ClientId;

    @Value("${auth.oauth2.client_secret}")
    public String oauth2ClientSecret;

    @Autowired
    private SysUserService userService;

    @Resource
    public BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private TokenStore tokenStore;

    /**
     * 登录
     *
     * @return
     */
    @PostMapping("login/{username}/{password}")
    public ResponseResult login(@PathVariable String username, @PathVariable String password) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq(SysUser.COL_ACCOUNT, username);
        SysUser user = userService.getOne(wrapper);
        if (StringUtils.isEmpty(user)) {
            return ResponseResult.error("用户不存在");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseResult.error("密码错误");
        }

        // 通过 HTTP 获取token
        Map<String, Object> params = Maps.newHashMap();
        HashMap<Object, Object> result = new HashMap<>(1);
        params.put("username", username);
        params.put("password", password);
        params.put("grant_type", oauth2GrantType);
        params.put("client_id", oauth2ClientId);
        params.put("client_secret", oauth2ClientSecret);

        try {
            // 解析响应结果封装并返回
            String jsonString = HttpUtil.post(accessTokenUrl, params);
            Map<String, Object> jsonMap = (Map) JSONObject.parse(jsonString);
            String token = String.valueOf(jsonMap.get("access_token"));
            result.put("token", token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseResult.success(result);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("info")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseResult info() {
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        // 获取个人信息
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq(SysUser.COL_ACCOUNT, username);
        SysUser user = userService.getOne(wrapper);
        if (StrUtil.isEmpty(user.getRoleid())) {
            return ResponseResult.error("该用户未配置权限");
        }
        try {
            AuthorizationUser authorizationInfo = userService.getAuthorizationInfo(username);
            Map map = Maps.newHashMap("name", user.getName(), "role", "admin", "roles", authorizationInfo.getRoleCodes());
            map.put("permissions", authorizationInfo.getUrls());
            Map profile = (Map) Mapl.toMaplist(user);
            profile.put("dept", authorizationInfo.getDeptName());
            profile.put("roles", authorizationInfo.getRoleNames());
            map.put("profile", profile);
            return ResponseResult.success(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseResult.error("获取用户信息失败");
    }

    /**
     * 注销
     *
     * @return
     */
    @PostMapping("logout")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseResult logout(HttpServletRequest request) {
        // 获取 token
        String token = request.getHeader("Authorization");
        // 删除 token 以注销
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return ResponseResult.success();
    }

}
