package com.lucky.shop.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.auth.domain.TSysUser;
import com.lucky.shop.auth.mapper.TSysUserMapper;
import com.lucky.shop.auth.service.TSysUserService;
import org.springframework.stereotype.Service;

/**
 * 用户ServiceImpl
 *
 * @Author Guo Huaijian
 * @Date 2020/5/16 21:42
 */
@Service
public class TSysUserServiceImpl extends ServiceImpl<TSysUserMapper, TSysUser> implements TSysUserService{

}
