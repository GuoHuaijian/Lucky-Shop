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
 * 定时任务
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 23:34
 */
@Data
@TableName(value = "t_sys_task")
public class SysTask implements Serializable {
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
     * 是否允许并发
     */
    @TableField(value = "concurrent")
    private Boolean concurrent;

    /**
     * 定时规则
     */
    @NotBlank(message = "定时规则不能为空")
    @TableField(value = "cron")
    private String cron;

    /**
     * 执行参数
     */
    @TableField(value = "data")
    private String data;

    /**
     * 是否禁用
     */
    @TableField(value = "disabled")
    private Boolean disabled;

    /**
     * 执行时间
     */
    @TableField(value = "exec_at")
    private Date execAt;

    /**
     * 执行结果
     */
    @TableField(value = "exec_result")
    private String execResult;

    /**
     * 执行类
     */
    @NotBlank(message = "执行类不能为空")
    @TableField(value = "job_class")
    private String jobClass;

    /**
     * 任务组名
     */
    @TableField(value = "job_group")
    private String jobGroup;

    /**
     * 任务名
     */
    @NotBlank(message = "名称不能为空")
    @TableField(value = "name")
    private String name;

    /**
     * 任务说明
     */
    @TableField(value = "note")
    private String note;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_MODIFY_BY = "modify_by";

    public static final String COL_MODIFY_TIME = "modify_time";

    public static final String COL_CONCURRENT = "concurrent";

    public static final String COL_CRON = "cron";

    public static final String COL_DATA = "data";

    public static final String COL_DISABLED = "disabled";

    public static final String COL_EXEC_AT = "exec_at";

    public static final String COL_EXEC_RESULT = "exec_result";

    public static final String COL_JOB_CLASS = "job_class";

    public static final String COL_JOB_GROUP = "job_group";

    public static final String COL_NAME = "name";

    public static final String COL_NOTE = "note";
}
