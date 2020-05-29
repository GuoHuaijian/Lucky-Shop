package com.lucky.shop.admin.auth.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单
 * @Author Guo Huaijian
 * @Date 2020/5/17 18:49
 */
@Data
@TableName(value = "t_sys_menu")
public class TSysMenu implements Serializable {
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
     * 编号
     */
    @TableField(value = "code")
    private String code;

    /**
     * 組件配置
     */
    @TableField(value = "component")
    private String component;

    /**
     * 是否隐藏
     */
    @TableField(value = "hidden")
    private Byte hidden;

    /**
     * 图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 是否是菜单1:菜单,0:按钮
     */
    @TableField(value = "ismenu")
    private Integer ismenu;

    /**
     * 是否默认打开1:是,0:否
     */
    @TableField(value = "isopen")
    private Integer isopen;

    /**
     * 级别
     */
    @TableField(value = "levels")
    private Integer levels;

    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 顺序
     */
    @TableField(value = "num")
    private Integer num;

    /**
     * 父菜单编号
     */
    @TableField(value = "pcode")
    private String pcode;

    /**
     * 递归父级菜单编号
     */
    @TableField(value = "pcodes")
    private String pcodes;

    /**
     * 状态1:启用,0:禁用
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 鼠标悬停提示信息
     */
    @TableField(value = "tips")
    private String tips;

    /**
     * 链接
     */
    @TableField(value = "url")
    private String url;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_MODIFY_BY = "modify_by";

    public static final String COL_MODIFY_TIME = "modify_time";

    public static final String COL_CODE = "code";

    public static final String COL_COMPONENT = "component";

    public static final String COL_HIDDEN = "hidden";

    public static final String COL_ICON = "icon";

    public static final String COL_ISMENU = "ismenu";

    public static final String COL_ISOPEN = "isopen";

    public static final String COL_LEVELS = "levels";

    public static final String COL_NAME = "name";

    public static final String COL_NUM = "num";

    public static final String COL_PCODE = "pcode";

    public static final String COL_PCODES = "pcodes";

    public static final String COL_STATUS = "status";

    public static final String COL_TIPS = "tips";

    public static final String COL_URL = "url";
}
