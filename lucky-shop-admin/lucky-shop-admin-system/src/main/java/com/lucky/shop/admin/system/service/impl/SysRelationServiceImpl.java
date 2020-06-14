package com.lucky.shop.admin.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.system.domain.SysRelation;
import com.lucky.shop.admin.system.mapper.SysRelationMapper;
import com.lucky.shop.admin.system.service.SysRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 菜单角色关系
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 22:50
 */
@Service
public class SysRelationServiceImpl extends ServiceImpl<SysRelationMapper, SysRelation> implements SysRelationService {

    @Autowired
    private SysRelationMapper relationMapper;

    /**
     * 删除权限
     *
     * @param roleId
     * @return
     */
    @Override
    public int deleteByRoleId(Long roleId) {
        return relationMapper.deleteByRoleId(roleId);
    }
}
