package com.lucky.shop.admin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.system.domain.SysUser;
import com.lucky.shop.admin.system.mapper.SysUserMapper;
import com.lucky.shop.admin.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 账号
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 18:04
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    /**
     * 根据登录账号获取用户信息
     *
     * @param account
     * @return
     */
    @Override
    public SysUser getUserByAccount(String account) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq(SysUser.COL_ACCOUNT, account);
        SysUser sysUser = userMapper.selectOne(wrapper);
        return sysUser;
    }
}
