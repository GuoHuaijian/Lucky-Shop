package com.lucky.shop.admin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.admin.system.domain.SysDept;
import com.lucky.shop.admin.system.domain.vo.DeptNode;

import java.util.List;

/**
 * 部门
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 17:46
 */
public interface SysDeptService extends IService<SysDept> {

    /**
     * 获取所有节点
     *
     * @return
     */
    List<DeptNode> DeptList();

    /**
     * 编辑部门
     *
     * @param dept
     */
    void saveDept(SysDept dept);

    /**
     * 删除部门
     *
     * @param id
     * @return
     */
    void remove(Long id);

}
