package com.lucky.shop.admin.message.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 消息发送者
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 19:10
 */
@Data
@TableName(value = "t_message_sender")
public class MessageSender implements Serializable {
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
     * 发送类
     */
    @NotBlank(message = "发送类不能为空")
    @TableField(value = "class_name")
    private String className;

    /**
     * 名称
     */
    @NotBlank(message = "名称并能为空")
    @TableField(value = "name")
    private String name;

    /**
     * 短信运营商模板编号
     */
    @TableField(value = "tpl_code")
    private String tplCode;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_MODIFY_BY = "modify_by";

    public static final String COL_MODIFY_TIME = "modify_time";

    public static final String COL_CLASS_NAME = "class_name";

    public static final String COL_NAME = "name";

    public static final String COL_TPL_CODE = "tpl_code";
}
