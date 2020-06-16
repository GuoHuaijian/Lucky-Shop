package com.lucky.shop.admin.message.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

 /**
  * 历史消息
  *
 * @Author Guo Huaijian
 * @Date 2020/6/15 18:58
 */
@Data
@TableName(value = "t_message")
public class Message implements Serializable {
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
     * 消息内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 接收者
     */
    @TableField(value = "receiver")
    private String receiver;

    /**
     * 消息类型,0:初始,1:成功,2:失败
     */
    @TableField(value = "state")
    private String state;

    /**
     * 模板编码
     */
    @TableField(value = "tpl_code")
    private String tplCode;

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

    public static final String COL_CONTENT = "content";

    public static final String COL_RECEIVER = "receiver";

    public static final String COL_STATE = "state";

    public static final String COL_TPL_CODE = "tpl_code";

    public static final String COL_TYPE = "type";
}
