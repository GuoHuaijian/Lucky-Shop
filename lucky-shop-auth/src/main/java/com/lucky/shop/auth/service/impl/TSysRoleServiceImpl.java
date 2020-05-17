package com.lucky.shop.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.auth.domain.TSysRole;
import com.lucky.shop.auth.mapper.TSysRoleMapper;
import com.lucky.shop.auth.service.TSysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限ServiceImpl
 * @Author Guo Huaijian
 * @Date 2020/5/17 18:49
 */
@Service
public class TSysRoleServiceImpl extends ServiceImpl<TSysRoleMapper, TSysRole> implements TSysRoleService{

}
