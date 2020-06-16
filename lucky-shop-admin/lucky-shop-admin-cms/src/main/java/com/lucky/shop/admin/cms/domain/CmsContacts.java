package com.lucky.shop.admin.cms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 邀约信息
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 14:42
 */
@Data
@TableName(value = "t_cms_contacts")
public class CmsContacts implements Serializable {
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

    /**
     * 电子邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 联系电话
     */
    @TableField(value = "mobile")
    private String mobile;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 邀约人名称
     */
    @TableField(value = "user_name")
    private String userName;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_MODIFY_BY = "modify_by";

    public static final String COL_MODIFY_TIME = "modify_time";

    public static final String COL_EMAIL = "email";

    public static final String COL_MOBILE = "mobile";

    public static final String COL_REMARK = "remark";

    public static final String COL_USER_NAME = "user_name";
}
