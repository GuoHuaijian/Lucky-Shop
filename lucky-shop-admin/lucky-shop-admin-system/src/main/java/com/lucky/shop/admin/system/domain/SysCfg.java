package com.lucky.shop.admin.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统参数
 *
 * @Author Guo Huaijian
 * @Date 2020/6/5 16:49
 */
@Data
@TableName(value = "t_sys_cfg")
public class SysCfg implements Serializable {
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
     * 备注
     */
    @TableField(value = "cfg_desc")
    private String cfgDesc;

    /**
     * 参数名
     */
    @NotBlank(message = "参数名不能为空")
    @TableField(value = "cfg_name")
    private String cfgName;

    /**
     * 参数值
     */
    @NotBlank(message = "参数值不能为空")
    @TableField(value = "cfg_value")
    private String cfgValue;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_MODIFY_BY = "modify_by";

    public static final String COL_MODIFY_TIME = "modify_time";

    public static final String COL_CFG_DESC = "cfg_desc";

    public static final String COL_CFG_NAME = "cfg_name";

    public static final String COL_CFG_VALUE = "cfg_value";
}
