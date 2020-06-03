package com.lucky.shop.auth.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 自定义Authentication对象
 *
 * @Author Guo Huaijian
 * @Date 2020/5/17 18:23
 */
@Data
public class AuthorizationUser implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 账号
     */
    private String account;
    private String password;
    private String name;
    private Long deptId;
    /**
     * 角色集
     */
    private List<Long> roleList;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 角色名称集
     */
    private List<String> roleNames;
    /**
     * 角色编码
     */
    private List<String> roleCodes;
    /**
     * 资源路径
     */
    private Set<String> urls;
    /**
     * 资源编码
     */
    private Set<String> permissions;
}
