package com.lucky.shop.admin.ops.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录日志
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 13:10
 */
@Data
@TableName(value = "t_sys_login_log")
public class SysLoginLog implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "ip")
    private String ip;

    @TableField(value = "logname")
    private String logname;

    @TableField(value = "message")
    private String message;

    @TableField(value = "succeed")
    private String succeed;

    @TableField(value = "userid")
    private Integer userid;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_IP = "ip";

    public static final String COL_LOGNAME = "logname";

    public static final String COL_MESSAGE = "message";

    public static final String COL_SUCCEED = "succeed";

    public static final String COL_USERID = "userid";
}
