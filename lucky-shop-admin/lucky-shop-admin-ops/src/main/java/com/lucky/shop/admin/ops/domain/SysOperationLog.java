package com.lucky.shop.admin.ops.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 13:10
 */
@Data
@TableName(value = "t_sys_operation_log")
public class SysOperationLog implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "classname")
    private String classname;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "logname")
    private String logname;

    @TableField(value = "logtype")
    private String logtype;

    /**
     * 详细信息
     */
    @TableField(value = "message")
    private String message;

    @TableField(value = "method")
    private String method;

    @TableField(value = "succeed")
    private String succeed;

    @TableField(value = "userid")
    private Integer userid;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CLASSNAME = "classname";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_LOGNAME = "logname";

    public static final String COL_LOGTYPE = "logtype";

    public static final String COL_MESSAGE = "message";

    public static final String COL_METHOD = "method";

    public static final String COL_SUCCEED = "succeed";

    public static final String COL_USERID = "userid";
}
