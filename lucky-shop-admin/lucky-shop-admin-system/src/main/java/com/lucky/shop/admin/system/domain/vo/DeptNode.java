package com.lucky.shop.admin.system.domain.vo;

import com.lucky.shop.admin.system.domain.SysDept;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * DeptNode
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 17:54
 */
@Data
public class DeptNode extends SysDept {

    private List<DeptNode> children = new ArrayList<>(10);
}
