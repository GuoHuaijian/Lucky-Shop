package com.lucky.shop.auth.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体
 *
 * @Author Guo Huaijian
 * @Date 2020/5/16 21:42
 */
@Data
@TableName(value = "t_sys_user")
public class TSysUser implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private Long createBy;

    /**
     * 创建时间/注册时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 最后更新人
     */
    @TableField(value = "modify_by")
    private Long modifyBy;

    /**
     * 最后更新时间
     */
    @TableField(value = "modify_time")
    private Date modifyTime;

    @TableField(value = "account")
    private String account;

    @TableField(value = "avatar")
    private String avatar;

    @TableField(value = "birthday")
    private Date birthday;

    @TableField(value = "deptid")
    private Long deptid;

    @TableField(value = "email")
    private String email;

    @TableField(value = "name")
    private String name;

    @TableField(value = "password")
    private String password;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "roleid")
    private String roleid;

    @TableField(value = "salt")
    private String salt;

    @TableField(value = "sex")
    private Integer sex;

    @TableField(value = "status")
    private Integer status;

    @TableField(value = "version")
    private Integer version;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_MODIFY_BY = "modify_by";

    public static final String COL_MODIFY_TIME = "modify_time";

    public static final String COL_ACCOUNT = "account";

    public static final String COL_AVATAR = "avatar";

    public static final String COL_BIRTHDAY = "birthday";

    public static final String COL_DEPTID = "deptid";

    public static final String COL_EMAIL = "email";

    public static final String COL_NAME = "name";

    public static final String COL_PASSWORD = "password";

    public static final String COL_PHONE = "phone";

    public static final String COL_ROLEID = "roleid";

    public static final String COL_SALT = "salt";

    public static final String COL_SEX = "sex";

    public static final String COL_STATUS = "status";

    public static final String COL_VERSION = "version";
}
