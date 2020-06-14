package com.lucky.shop.admin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.system.domain.SysDept;
import com.lucky.shop.admin.system.domain.vo.DeptNode;
import com.lucky.shop.admin.system.mapper.SysDeptMapper;
import com.lucky.shop.admin.system.service.SysDeptService;
import com.lucky.shop.common.core.tool.LogObjectHolder;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 17:46
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    /**
     * 获取所有节点
     *
     * @return
     */
    @Override
    public List<DeptNode> DeptList() {
        List<DeptNode> list = this.queryAllNode();
        return list;
    }

    /**
     * 编辑部门
     *
     * @param dept
     */
    @Override
    public void saveDept(SysDept dept) {
        if (dept.getId() != null) {
            SysDept old = this.getById(dept.getId());
            LogObjectHolder.me().set(old);
            old.setPid(dept.getPid());
            old.setSimplename(dept.getSimplename());
            old.setFullname(dept.getFullname());
            old.setNum(dept.getNum());
            old.setTips(dept.getTips());
            this.deptSetPids(old);
            this.updateById(old);
        } else {
            this.deptSetPids(dept);
            this.save(dept);
        }
    }

    /**
     * 删除部门
     *
     * @param id
     */
    @Override
    public void remove(Long id) {
        this.deleteDept(id);
    }


    public List<DeptNode> queryAllNode() {
        List<SysDept> list = this.list();
        return generateTree(list);
    }

    private List<DeptNode> generateTree(List<SysDept> list) {
        List<DeptNode> nodes = new ArrayList<>(20);
        for (SysDept dept : list) {
            DeptNode deptNode = new DeptNode();
            BeanUtils.copyProperties(dept, deptNode);
            nodes.add(deptNode);
        }
        for (DeptNode deptNode : nodes) {
            for (DeptNode child : nodes) {
                if (child.getPid().intValue() == deptNode.getId().intValue()) {
                    deptNode.getChildren().add(child);
                }
            }
        }
        List<DeptNode> result = new ArrayList<>(20);
        for (DeptNode node : nodes) {
            if (node.getPid().intValue() == 0) {
                result.add(node);
            }
        }
        return result;
    }

    public void deptSetPids(SysDept dept) {
        if (dept.getPid() == null || dept.getPid().intValue() == 0) {
            dept.setPid(0L);
            dept.setPids("[0],");
        } else {
            Long pid = dept.getPid();
            SysDept temp = getById(pid);
            String pids = "";
            if (temp != null) {
                pids = temp.getPids();
            }
            dept.setPid(pid);
            dept.setPids(pids + "[" + pid + "],");
        }
    }

    public void deleteDept(Long deptId) {
        SysDept dept = getById(deptId);
        QueryWrapper<SysDept> wrapper = new QueryWrapper<>();
        wrapper.like(SysDept.COL_PIDS, dept.getId());
        List<SysDept> subDepts = this.list(wrapper);
        this.removeByIds(subDepts);
        this.removeById(dept);
    }
}
