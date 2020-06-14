package com.lucky.shop.admin.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务日志
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 23:34
 */
@Data
@TableName(value = "t_sys_task_log")
public class SysTaskLog implements Serializable {

    public static final int EXE_FAILURE_RESULT = 0;
    public static final int EXE_SUCCESS_RESULT = 1;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 执行时间
     */
    @TableField(value = "exec_at")
    private Date execAt;

    /**
     * 执行结果（成功:1、失败:0)
     */
    @TableField(value = "exec_success")
    private Integer execSuccess;

    @TableField(value = "id_task")
    private Long idTask;

    /**
     * 抛出异常
     */
    @TableField(value = "job_exception")
    private String jobException;

    /**
     * 任务名
     */
    @TableField(value = "name")
    private String name;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_EXEC_AT = "exec_at";

    public static final String COL_EXEC_SUCCESS = "exec_success";

    public static final String COL_ID_TASK = "id_task";

    public static final String COL_JOB_EXCEPTION = "job_exception";

    public static final String COL_NAME = "name";
}
