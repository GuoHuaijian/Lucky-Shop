package com.lucky.shop.auth.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 部门
 *
 * @Author Guo Huaijian
 * @Date 2020/5/17 18:49
 */
@Data
@TableName(value = "t_sys_dept")
public class TSysDept implements Serializable {
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

    @TableField(value = "fullname")
    private String fullname;

    @TableField(value = "num")
    private Integer num;

    @TableField(value = "pid")
    private Long pid;

    @TableField(value = "pids")
    private String pids;

    @TableField(value = "simplename")
    private String simplename;

    @TableField(value = "tips")
    private String tips;

    @TableField(value = "version")
    private Integer version;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_MODIFY_BY = "modify_by";

    public static final String COL_MODIFY_TIME = "modify_time";

    public static final String COL_FULLNAME = "fullname";

    public static final String COL_NUM = "num";

    public static final String COL_PID = "pid";

    public static final String COL_PIDS = "pids";

    public static final String COL_SIMPLENAME = "simplename";

    public static final String COL_TIPS = "tips";

    public static final String COL_VERSION = "version";
}
