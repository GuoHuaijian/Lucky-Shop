package com.lucky.shop.admin.system.api.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 账号
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 18:04
 */
@Data
public class SysUser implements Serializable {


    private Long id;

    /**
     * 创建人
     */
    private Long createBy;

    /**
     * 创建时间/注册时间
     */
    private Date createTime;

    /**
     * 最后更新人
     */
    private Long modifyBy;

    /**
     * 最后更新时间
     */
    private Date modifyTime;

    private String account;

    private String avatar;

    private Date birthday;

    private Long deptid;

    private String email;

    private String name;

    private String password;

    private String phone;

    private String roleid;

    private String salt;

    private Integer sex;

    private Integer status;

    private Integer version;

}
