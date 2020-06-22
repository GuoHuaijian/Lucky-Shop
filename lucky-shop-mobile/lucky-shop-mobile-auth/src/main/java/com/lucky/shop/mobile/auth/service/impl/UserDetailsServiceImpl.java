package com.lucky.shop.mobile.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.lucky.shop.mobile.auth.domain.ShopUser;
import com.lucky.shop.mobile.auth.service.ShopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 自定义认证
 *
 * @Author Guo Huaijian
 * @Date 2020/5/16 21:30
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ShopUserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        QueryWrapper<ShopUser> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopUser.COL_MOBILE, s);
        ShopUser user = userService.getOne(wrapper);
        if (!StringUtils.isEmpty(user)) {
            // 设置后台用户拥有Admin权限,可以从数据库获取权限
            List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("mobile");
            grantedAuthorities.add(grantedAuthority);
            return new User(user.getMobile(), user.getPassword(), grantedAuthorities);
        }
        return null;
    }
}
