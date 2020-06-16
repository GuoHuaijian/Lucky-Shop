package com.lucky.shop.admin.message.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息模板
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 19:25
 */
@Data
@TableName(value = "t_message_template")
public class MessageTemplate implements Serializable {
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
     * 发送条件
     */
    @TableField(value = "cond")
    private String cond;

    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 发送者id
     */
    @TableField(value = "id_message_sender")
    private Long idMessageSender;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 消息类型,0:短信,1:邮件
     */
    @TableField(value = "type")
    private String type;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_MODIFY_BY = "modify_by";

    public static final String COL_MODIFY_TIME = "modify_time";

    public static final String COL_CODE = "code";

    public static final String COL_COND = "cond";

    public static final String COL_CONTENT = "content";

    public static final String COL_ID_MESSAGE_SENDER = "id_message_sender";

    public static final String COL_TITLE = "title";

    public static final String COL_TYPE = "type";
}
